package ru.javawebinar.topjava.Service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServiceInMemoryImpl implements MealService {
    private static final Map<Integer, Meal> MEAL_REPOSITORY_MAP = new ConcurrentHashMap<>();

    static {
        MEAL_REPOSITORY_MAP.put(1, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        MEAL_REPOSITORY_MAP.put(2, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        MEAL_REPOSITORY_MAP.put(3, new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        MEAL_REPOSITORY_MAP.put(4, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        MEAL_REPOSITORY_MAP.put(5, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        MEAL_REPOSITORY_MAP.put(6, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        MEAL_REPOSITORY_MAP.put(7, new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static final AtomicInteger MEAL_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Meal meal) {

    }

    @Override
    public List<Meal> readAll() {
        return new ArrayList<>(MEAL_REPOSITORY_MAP.values());
    }

    @Override
    public Meal read(int id) {
        return MEAL_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Meal meal, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
