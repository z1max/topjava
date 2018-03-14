package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            User admin = new User(null, "userName", "email", "password", Role.ROLE_ADMIN);
            User user = new User(null, "user", "email1", "password", Role.ROLE_ADMIN);
            adminUserController.create(admin);
            adminUserController.create(user);

            System.out.println(admin);
            System.out.println(user);

            Meal meal1 = new Meal(null, LocalDateTime.now(), "meal 1", 500);
            Meal meal2 = new Meal(null, LocalDateTime.now(), "meal 2", 700);
            mealRestController.create(meal1);
            mealRestController.create(meal2);

            System.out.println(meal1);
            System.out.println(meal2);

            System.out.println(mealRestController.getAll());

            AuthorizedUser.setId(2);

            mealRestController.delete(7);
        }
    }
}
