package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.Dao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private final Dao<Meal, Long> mealDao;

    public MealServlet() {
        mealDao = new MealDaoInMemory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "null" : req.getParameter("action");

        switch (action.toLowerCase()) {
            case "delete":
                mealDao.delete(Long.parseLong(req.getParameter("id")));
                log.debug("meal deleted");
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

        if (idString == null || idString.isEmpty()) {
            mealDao.create(new Meal(LocalDateTime.parse(datetime, DATE_TIME_FORMATTER),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories"))));
            log.debug("meal created");
        } else {
            long id = Long.parseLong(idString);
            mealDao.update(new Meal(id, LocalDateTime.parse(datetime, DATE_TIME_FORMATTER),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories"))));
            log.debug("meal updated");
        }

        resp.sendRedirect("meals");
    }
}
