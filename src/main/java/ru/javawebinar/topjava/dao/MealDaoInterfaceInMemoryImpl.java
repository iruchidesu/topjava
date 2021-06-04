package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealDaoInterfaceInMemoryImpl implements DaoInterface {
    private static final Map<Long, Meal> MEAL_REPOSITORY_MAP = new ConcurrentHashMap<>();

    private static final AtomicLong MEAL_ID = new AtomicLong();

    public MealDaoInterfaceInMemoryImpl() {
        initData();
    }

    @Override
    public void create(Meal meal) {
        meal.setId(MEAL_ID.incrementAndGet());
        MEAL_REPOSITORY_MAP.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(MEAL_REPOSITORY_MAP.values());
    }

    @Override
    public Meal getOne(long id) {
        return MEAL_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Meal meal, long id) {
        if (MEAL_REPOSITORY_MAP.containsKey(id)) {
            meal.setId(id);
            MEAL_REPOSITORY_MAP.put(id, meal);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        return MEAL_REPOSITORY_MAP.remove(id) != null;
    }

    private void initData() {
        MEAL_REPOSITORY_MAP.put(1L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        MEAL_REPOSITORY_MAP.put(2L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        MEAL_REPOSITORY_MAP.put(3L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        MEAL_REPOSITORY_MAP.put(4L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        MEAL_REPOSITORY_MAP.put(5L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        MEAL_REPOSITORY_MAP.put(6L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        MEAL_REPOSITORY_MAP.put(7L, new Meal(MEAL_ID.incrementAndGet(), LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
}
