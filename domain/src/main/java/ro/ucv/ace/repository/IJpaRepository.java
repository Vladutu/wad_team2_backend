package ro.ucv.ace.repository;

import ro.ucv.ace.repository.components.ICondition;
import ro.ucv.ace.repository.components.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * This interface provides methods for basic operations on JPA entities.
 *
 * @param <S>  type of the interface which the entity implements
 * @param <T>  type of the entity
 * @param <ID> type of the entity primary key
 */
public interface IJpaRepository<S, T extends S, ID extends Serializable> {

    /**
     * Returns all entities of the type.
     *
     * @return list of entities of type T
     */
    List<S> findAll();

    /**
     * Returns a IPage of entities meeting the paging restriction provided in the IPage object.
     *
     * @param page page
     * @return a page of entities
     */
    List<S> findAll(IPage page);

    /**
     * Returns all entities meeting the condition restriction provided in the ICondition object.
     *
     * @param condition condition
     * @return list of entities meeting the condition
     */
    List<S> findAllWhere(ICondition<T> condition);

    /**
     * Returns the entity whose id is the same as the method parameter.
     *
     * @param id id of the entity
     * @return entity
     */
    S findOne(ID id);

    /**
     * Returns the entity that meets the condition restriction provided in the ICondition object.
     *
     * @param condition condition
     * @return searched entity
     */
    S findOneWhere(ICondition<T> condition);


    /**
     * Saves the entity.
     *
     * @param t entity to be saved
     * @return saved entity
     */
    S save(S t);

    /**
     * Deletes the entity whose id is  the same as the method parameter.
     *
     * @param id id of the entity
     * @return deleted entity
     */
    S delete(ID id);


    /**
     * Returns the number of entities in the database.
     *
     * @return number of entities
     */
    Long count();
}
