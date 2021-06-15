package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак user2", 500), 2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед user2", 1600), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.putIfAbsent(userId, new ConcurrentHashMap<>());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        Map<Integer, Meal> userMeal = repository.get(userId);
        if (userMeal == null) {
            return null;
        }
        Meal updatedMeal = userMeal.get(meal.getId());
        if (updatedMeal != null && updatedMeal.getUserId() == userId) {
            meal.setUserId(userId);
            return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        if (userMeal == null) {
            return false;
        }
        Meal meal = userMeal.get(id);
        if (meal != null && meal.getUserId() == userId) {
            return repository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        if (userMeal == null) {
            return null;
        }
        Meal meal = userMeal.get(id);
        return meal != null && meal.getUserId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return filterByPredicate(userId, meal -> DateTimeUtil.isBetweenClosed(meal.getDate(), startDate, endDate));
    }

    public List<Meal> filterByPredicate(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> userMeal = repository.get(userId);
        return userMeal == null ? Collections.emptyList() :
                userMeal.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDate, Comparator.reverseOrder())
                                .thenComparing(Meal::getTime, Comparator.reverseOrder()))
                        .collect(Collectors.toList());
    }
}

