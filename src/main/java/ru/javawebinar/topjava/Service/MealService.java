package ru.javawebinar.topjava.Service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
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
    List<Meal> readAll();

    /**
     * Return meal by ID
     *
     * @param id - ID meal
     * @return - object meal
     */
    Meal read(int id);

    /**
     * Update meal with ID
     *
     * @param meal - meal object
     * @param id   - id meal
     * @return - true is data is updated, else false
     */
    boolean update(Meal meal, int id);

    /**
     * Delete meal
     *
     * @param id - id meal
     * @return - true if meal is deleted, else false
     */
    boolean delete(int id);
}
