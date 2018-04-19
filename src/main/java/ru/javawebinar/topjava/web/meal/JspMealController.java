package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class JspMealController {

    @Autowired
    MealService service;

    @GetMapping("/meals")
    public String meals(Model model){
        int userId = AuthorizedUser.id();
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/meals/delete")
    public String delete(Model model, HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        service.delete(id, AuthorizedUser.id());
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "redirect:/meals";
    }

    @GetMapping("/meals/update")
    public String update(Model model, HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        model.addAttribute("meal", service.get(id, AuthorizedUser.id()));
        return "mealForm";
    }

    @PostMapping("/meals/update")
    public String update(HttpServletRequest request){
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        service.update(meal, AuthorizedUser.id());
        return "redirect:/meals";
    }
}
