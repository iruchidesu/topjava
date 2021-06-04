package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.Service.MealService;
import ru.javawebinar.topjava.Service.MealServiceInMemoryImpl;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private static final int CALORIES_PER_DAY = 2000;

    private final MealService mealService;

    public MealServlet() {
        mealService = new MealServiceInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("meals list");

        List<MealTo> mealToList = MealsUtil.filteredByStreams(mealService.readAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        req.setAttribute("mealToList", mealToList);

        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
