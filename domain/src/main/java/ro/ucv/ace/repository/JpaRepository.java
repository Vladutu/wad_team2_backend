package ro.ucv.ace.repository;

import ro.ucv.ace.repository.components.Condition;
import ro.ucv.ace.repository.components.Page;

import java.io.Serializable;
import java.util.List;

/**
 * This interface provides methods for basic operations on JPA entities.
 *
 * @param <S>  type of the interface which the entity implements
 * @param <T>  type of the entity
 * @param <ID> type of the entity primary key
 */
public interface JpaRepository<S, T extends S, ID extends Serializable> {

    /**
     * Returns all entities of the type.
     *
     * @return list of entities of type T
     */
    List<S> findAll();

    /**
     * Returns a Page of entities meeting the paging restriction provided in the Page object.
     *
     * @param page page
     * @return a page of entities
     */
    List<S> findAll(Page page);

    /**
     * Returns all entities meeting the condition restriction provided in the Condition object.
     *
     * @param condition condition
     * @return list of entities meeting the condition
     */
    List<S> findAllWhere(Condition<T> condition);

    /**
     * Returns the entity whose id is the same as the method parameter.
     *
     * @param id id of the entity
     * @return entity
     */
    S findOne(ID id);


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
