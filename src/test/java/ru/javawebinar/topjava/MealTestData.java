package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID_1 = START_SEQ + 2;
    public static final int USER_MEAL_ID_2 = START_SEQ + 3;
    public static final int USER_MEAL_ID_3 = START_SEQ + 4;
    public static final int USER_MEAL_ID_4 = START_SEQ + 5;
    public static final int USER_MEAL_ID_5 = START_SEQ + 6;
    public static final int USER_MEAL_ID_6 = START_SEQ + 7;
    public static final int ADMIN_MEAL_ID_1 = START_SEQ + 8;
    public static final int ADMIN_MEAL_ID_2 = START_SEQ + 9;
    public static final int MEAL_NOT_FOUND_ID = 10;

    public static final LocalDate START_DATE = LocalDate.of(2021, 6, 17);
    public static final LocalDate END_DATE = LocalDate.of(2021, 6, 17);

    public static final Meal userMeal1 = new Meal(USER_MEAL_ID_1, LocalDateTime.of(2021, Month.JUNE, 17, 8, 0), "Завтрак", 500);
    public static final Meal userMeal2 = new Meal(USER_MEAL_ID_2, LocalDateTime.of(2021, Month.JUNE, 17, 13, 0), "Обед", 1000);
    public static final Meal userMeal3 = new Meal(USER_MEAL_ID_3, LocalDateTime.of(2021, Month.JUNE, 17, 18, 0), "Ужин", 500);
    public static final Meal userMeal4 = new Meal(USER_MEAL_ID_4, LocalDateTime.of(2021, Month.JUNE, 16, 9, 0), "Завтрак", 400);
    public static final Meal userMeal5 = new Meal(USER_MEAL_ID_5, LocalDateTime.of(2021, Month.JUNE, 16, 13, 30), "Обед", 1300);
    public static final Meal userMeal6 = new Meal(USER_MEAL_ID_6, LocalDateTime.of(2021, Month.JUNE, 16, 18, 35), "Ужин", 500);
    public static final Meal adminMeal1 = new Meal(ADMIN_MEAL_ID_1, LocalDateTime.of(2021, Month.JUNE, 17, 8, 0), "Завтрак", 400);
    public static final Meal adminMeal2 = new Meal(ADMIN_MEAL_ID_2, LocalDateTime.of(2021, Month.JUNE, 17, 14, 30), "Обед", 1300);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JUNE, 18, 8, 20), "Завтрак", 550);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal1);
        updated.setDateTime(LocalDateTime.of(2021, Month.JUNE, 17, 14, 0));
        updated.setDescription("Полдник");
        updated.setCalories(150);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
