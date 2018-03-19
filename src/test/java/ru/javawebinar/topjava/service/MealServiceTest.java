package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Test
    public void get() {
        Meal actual =  mealService.get(MealTestData.MEAL1_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL1);

        actual =  mealService.get(MealTestData.MEAL2_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL2);

        actual =  mealService.get(MealTestData.MEAL3_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL3);

        actual =  mealService.get(MealTestData.MEAL4_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL4);

        actual =  mealService.get(MealTestData.MEAL5_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL5);

        actual =  mealService.get(MealTestData.MEAL6_ID, USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL6);
    }

    @Test(expected = NotFoundException.class)
    public void getWithWrongUserId(){
        mealService.get(MealTestData.MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        mealService.get(1, USER_ID);
    }

    @Test
    public void delete() {
        mealService.delete(MealTestData.MEAL1_ID, USER_ID);
        MealTestData.assertMatch(mealService.getAll(USER_ID), MealTestData.MEAL2, MealTestData.MEAL3, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWithWrongUserId(){
        mealService.delete(MealTestData.MEAL1_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        mealService.delete(1, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> actual = mealService.getBetweenDates(LocalDate.of(2018, 3, 16), LocalDate.of(2018, 3, 17), USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6);

        actual = mealService.getBetweenDates(LocalDate.of(2018, 3, 18), LocalDate.of(2018, 3, 19), USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL1, MealTestData.MEAL2, MealTestData.MEAL3);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> actual = mealService.getBetweenDateTimes(LocalDateTime.of(2018, 3, 17, 9, 0), LocalDateTime.of(2018, 3, 17, 11, 0), USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL4);

        actual = mealService.getBetweenDateTimes(LocalDateTime.of(2018, 3, 17, 9, 0), LocalDateTime.of(2018, 3, 18, 11, 0), USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL1, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6);

        actual = mealService.getBetweenDateTimes(LocalDateTime.of(2018, 3, 17, 9, 0), LocalDateTime.of(2018, 3, 18, 15, 0), USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL1, MealTestData.MEAL2, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6);
    }

    @Test
    public void getAll() {
        List<Meal> actual = mealService.getAll(USER_ID);
        MealTestData.assertMatch(actual, MealTestData.MEAL1, MealTestData.MEAL2, MealTestData.MEAL3, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MealTestData.MEAL1);
        updated.setCalories(200);
        updated.setDateTime(LocalDateTime.now());
        updated.setDescription("updated meal");
        mealService.update(updated, USER_ID);
        MealTestData.assertMatch(updated, mealService.get(MealTestData.MEAL1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateWithWrongUserId(){
        mealService.update(MealTestData.MEAL1, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "new meal", 333);
        Meal created = mealService.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MealTestData.assertMatch(mealService.getAll(USER_ID), MealTestData.MEAL1, MealTestData.MEAL2, MealTestData.MEAL3, MealTestData.MEAL4, MealTestData.MEAL5, MealTestData.MEAL6, newMeal);
    }
}