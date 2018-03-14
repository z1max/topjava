package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, AuthorizedUser.id()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Optional<Meal> currentMeal = Optional.of(repository.get(id));
        if (currentMeal.isPresent()) {
            if (currentMeal.get().getUserId() == userId) {
                repository.remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Optional<Meal> currentMeal = Optional.of(repository.get(id));
        if (currentMeal.isPresent()) {
            if (currentMeal.get().getId() == userId)
                return currentMeal.get();
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getAll(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        List<Meal> result = repository.values()
                .stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted((meal, other) -> -meal.getDateTime().compareTo(other.getDateTime()))
                .collect(Collectors.toList());

        return result == null ? Collections.emptyList() : result;
    }
}

