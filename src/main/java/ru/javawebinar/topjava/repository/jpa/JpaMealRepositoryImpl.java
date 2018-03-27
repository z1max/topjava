package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()){
            User user = em.getReference(User.class, userId);
            meal.setUser(user);
            em.persist(meal);
            return meal;
        } else if (em.createNamedQuery(Meal.UPDATE)
                .setParameter("dateTime", meal.getDateTime())
                .setParameter("calories", meal.getCalories())
                .setParameter("description", meal.getDescription())
                .setParameter("id", meal.getId())
                .setParameter("userId", userId)
                .executeUpdate() != 0){
            return meal;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List resultList = em.createNamedQuery(Meal.GET)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();

        if (resultList.isEmpty()){
            return null;
        } else {
            return (Meal) resultList.get(0);
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL).
                setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}