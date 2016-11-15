package ro.ucv.ace.repository.impl;

import org.jinq.jpa.JPAJinqStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import ro.ucv.ace.config.JinqSource;
import ro.ucv.ace.exception.DuplicateEntryException;
import ro.ucv.ace.exception.EntityNotFoundException;
import ro.ucv.ace.exception.ForeignKeyException;
import ro.ucv.ace.repository.IJpaRepository;
import ro.ucv.ace.repository.components.ICondition;
import ro.ucv.ace.repository.components.IExceptionParser;
import ro.ucv.ace.repository.components.IPage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Geo on 28.10.2016.
 */
public class JpaRepository<S, T extends S, ID extends Serializable> implements IJpaRepository<S, T, ID> {

    private final Class<T> persistentClass;

    private final Class<S> interfaceClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JinqSource jinqSource;

    @Autowired
    private IExceptionParser exceptionParser;


    public JpaRepository(Class<S> interfaceClass, Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        this.interfaceClass = interfaceClass;
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected JinqSource getJinqSource() {
        return this.jinqSource;
    }

    protected JPAJinqStream<T> streamAll() {
        return jinqSource.streamAll(entityManager, (Class<T>) persistentClass);
    }

    @Override
    public List<S> findAll() {
        List<T> ts = streamAll().toList();

        return convertListOfTToListOfS(ts);
    }

    @Override
    public List<S> findAll(IPage page) {
        List<T> ts = streamAll()
                .skip(page.getSkip())
                .limit(page.getLimit())
                .toList();

        return convertListOfTToListOfS(ts);
    }

    @Override
    public List<S> findAllWhere(ICondition<T> condition) {
        List<T> ts = streamAll()
                .where(condition)
                .toList();

        return convertListOfTToListOfS(ts);
    }

    @Override
    public S findOne(ID id) {
        T t = getEntityManager().find(persistentClass, id);

        if (t != null) {
            return t;
        }

        throw new EntityNotFoundException("Unable to find " + interfaceClass.getSimpleName() + " with id " + id);
    }

    @Override
    public S findOneWhere(ICondition<T> condition) {
        Optional<T> opt = streamAll()
                .where(condition)
                .findAny();

        if (opt.isPresent()) {
            return opt.get();
        }

        throw new EntityNotFoundException("Unable to find " + interfaceClass.getSimpleName() + " with the searched criteria");
    }

    @Override
    public S save(S t) {
        try {
            return getEntityManager().merge(t);
        } catch (JpaObjectRetrievalFailureException jbrfe) {
            throw new ForeignKeyException(exceptionParser.parseEntityNotFoundException(jbrfe));
        } catch (JpaSystemException jse) {
            throw new DuplicateEntryException(exceptionParser.parsePersistenceException(jse, interfaceClass));
        }
    }

    @Override
    public S delete(ID id) {
        S s = findOne(id);
        getEntityManager().remove(s);

        return s;
    }


    @Override
    public Long count() {
        return streamAll().count();
    }

    private List<S> convertListOfTToListOfS(List<T> list) {
        List<S> newList = new ArrayList<S>();
        list.forEach(newList::add);

        return newList;
    }
}
