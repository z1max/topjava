package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final int MEAL1_ID = 100002;
    public static final int MEAL2_ID = 100003;
    public static final int MEAL3_ID = 100004;
    public static final int MEAL4_ID = 100005;
    public static final int MEAL5_ID = 100006;
    public static final int MEAL6_ID = 100007;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.parse("2018-03-18 10:00:00", FORMATTER), "Breakfast", 500);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.parse("2018-03-18 14:00:00", FORMATTER), "Lunch", 1000);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, LocalDateTime.parse("2018-03-18 20:00:00", FORMATTER), "Dinner", 700);
    public static final Meal MEAL4 = new Meal(MEAL4_ID, LocalDateTime.parse("2018-03-17 10:00:00", FORMATTER), "Breakfast", 300);
    public static final Meal MEAL5 = new Meal(MEAL5_ID, LocalDateTime.parse("2018-03-17 14:00:00", FORMATTER), "Lunch", 800);
    public static final Meal MEAL6 = new Meal(MEAL6_ID, LocalDateTime.parse("2018-03-17 20:00:00", FORMATTER), "Dinner", 900);

    public static void assertMatch(Meal actual, Meal expected){
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected){
        List<Meal> exp = Arrays.asList(expected);
        exp.sort(Comparator.comparing(Meal::getDateTime).reversed());
        assertThat(actual).isEqualTo(exp);
    }
}
