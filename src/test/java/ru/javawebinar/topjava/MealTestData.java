package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int MEAL2_ID = START_SEQ + 3;
    public static final int MEAL3_ID = START_SEQ + 4;
    public static final int MEAL4_ID = START_SEQ + 5;
    public static final int MEAL5_ID = START_SEQ + 6;
    public static final int MEAL6_ID = START_SEQ + 7;
    public static final int MEAL7_ID = START_SEQ + 8;
    public static final int MEAL8_ID = START_SEQ + 9;
    public static final int MEAL_NOT_FOUND_ID = 10;

    public static final LocalDate starDateTime = LocalDate.of(2021, 6, 17);
    public static final LocalDate endDateTime = LocalDate.of(2021, 6, 17);

    public static final Meal meal1 = new Meal(MEAL1_ID, LocalDateTime.of(2021, Month.JUNE, 17, 8, 0), "Завтрак", 500);
    public static final Meal meal2 = new Meal(MEAL2_ID, LocalDateTime.of(2021, Month.JUNE, 17, 13, 0), "Обед", 1000);
    public static final Meal meal3 = new Meal(MEAL3_ID, LocalDateTime.of(2021, Month.JUNE, 17, 18, 0), "Ужин", 500);
    public static final Meal meal4 = new Meal(MEAL4_ID, LocalDateTime.of(2021, Month.JUNE, 16, 9, 0), "Завтрак", 400);
    public static final Meal meal5 = new Meal(MEAL5_ID, LocalDateTime.of(2021, Month.JUNE, 16, 13, 30), "Обед", 1300);
    public static final Meal meal6 = new Meal(MEAL6_ID, LocalDateTime.of(2021, Month.JUNE, 16, 18, 35), "Ужин", 500);
    public static final Meal meal7 = new Meal(MEAL7_ID, LocalDateTime.of(2021, Month.JUNE, 17, 9, 30), "Завтрак", 400);
    public static final Meal meal8 = new Meal(MEAL8_ID, LocalDateTime.of(2021, Month.JUNE, 17, 14, 30), "Обед", 1300);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, Month.JUNE, 18, 8, 20), "Завтрак", 550);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
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
