package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.admin;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    public void getMealByIdWithUser() {
        Meal actual = service.getMealByIdWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertMatch(actual, adminMeal1);
        ru.javawebinar.topjava.UserTestData.MATCHER.assertMatch(actual.getUser(), admin);
    }
}
