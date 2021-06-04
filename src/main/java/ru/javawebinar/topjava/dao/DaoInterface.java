package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DaoInterface {
    /**
     * Create new Meal
     *
     * @param meal - meal for create
     */
    void create(Meal meal);

    /**
     * Returns list all meals
     *
     * @return list of meals
     */
    List<Meal> getAll();

    /**
     * Return meal by ID
     *
     * @param id - ID meal
     * @return - object meal
     */
    Meal getOne(long id);

    /**
     * Update meal by ID
     *
     * @param meal - meal object
     * @param id   - id meal
     * @return - true is data is updated, else false
     */
    boolean update(Meal meal, long id);

    /**
     * Delete meal
     *
     * @param id - id meal
     * @return - true if meal is deleted, else false
     */
    boolean delete(long id);
}
