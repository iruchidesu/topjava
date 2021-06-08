package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryMealDao implements Dao<Meal, Long> {
    private final Map<Long, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong();

    public InMemoryMealDao() {
        initData();
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

    @Override
    public Meal create(Meal entity) {
        entity.setId(id.incrementAndGet());
        repository.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Meal getOne(Long id) {
        return repository.get(id);
    }

    @Override
    public Meal update(Meal entity) {
        return repository.computeIfPresent(entity.getId(), (k, v) -> entity);
    }

    @Override
    public Meal delete(Long id) {
        return repository.remove(id);
    }

}
