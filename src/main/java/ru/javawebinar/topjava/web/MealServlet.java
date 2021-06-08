package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.InMemoryMealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private Dao<Meal, Long> mealDao;

    @Override
    public void init() throws ServletException {
        mealDao = new InMemoryMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "null" : req.getParameter("action");

        switch (action.toLowerCase()) {
            case "delete":
                long id = Long.parseLong(req.getParameter("id"));
                mealDao.delete(id);
                log.debug("meal ID " + id + " has been deleted");
                resp.sendRedirect("meals");
                break;
            case "update":
                Meal meal = mealDao.getOne(Long.parseLong(req.getParameter("id")));
                req.setAttribute("meal", meal);
            case "insert":
                req.getRequestDispatcher("/editMeal.jsp").forward(req, resp);
                break;
            default:
                List<MealTo> mealToList = MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                req.setAttribute("mealToList", mealToList);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String idString = req.getParameter("id");
        String datetime = req.getParameter("datetime");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        if (idString == null || idString.isEmpty()) {
            mealDao.create(new Meal(localDateTime, description, calories));
            log.debug("new meal created");
        } else {
            long id = Long.parseLong(idString);
            mealDao.update(new Meal(id, localDateTime, description, calories));
            log.debug("meal ID " + id + " has been updated. New values {dateTime=" + localDateTime +
                    ", description=" + description + ", calories=" + calories + "}");
        }

        resp.sendRedirect("meals");
    }
}
