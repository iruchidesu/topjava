package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MealDaoInMemory implements Dao<Meal, Long> {
    private final Map<Long, Meal> mealRepositoryMap = new ConcurrentHashMap<>();
    private final AtomicLong mealId = new AtomicLong();

    public MealDaoInMemory() {
        initData();
    }

    @Override
    public void create(Meal entity) {
        entity.setId(mealId.incrementAndGet());
        mealRepositoryMap.put(entity.getId(), entity);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealRepositoryMap.values());
    }

    @Override
    public Meal getOne(Long id) {
        return mealRepositoryMap.get(id);
    }

    @Override
    public void update(Meal entity) {
        mealRepositoryMap.computeIfPresent(entity.getId(), (k, v) -> v = entity);
    }

    @Override
    public boolean delete(Long id) {
        return mealRepositoryMap.remove(id) != null;
    }

    private void initData() {
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }
}
