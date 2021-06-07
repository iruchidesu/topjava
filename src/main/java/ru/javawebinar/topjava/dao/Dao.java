package ru.javawebinar.topjava.dao;

import java.util.List;

public interface Dao<E, K> {
    /**
     * Create new entity
     *
     * @param entity - entity for create
     */
    void create(E entity);

    /**
     * Returns list all entity
     *
     * @return list of entity
     */
    List<E> getAll();

    /**
     * Return entity by ID
     *
     * @param id - ID entity
     * @return - object
     */
    E getOne(K id);

    /**
     * Update entity
     *
     * @param entity - entity object
     */
    void update(E entity);

    /**
     * Delete entity
     *
     * @param id - id entity
     * @return - true if entity is deleted, else false
     */
    boolean delete(K id);
}
