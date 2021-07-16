package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void createNoRoles() {
        User userNoRoles = getNew();
        userNoRoles.setRoles(Collections.emptyList());
        userNoRoles = service.create(userNoRoles);
        int newId = userNoRoles.id();
        User newUser = getNew();
        newUser.setRoles(Collections.emptyList());
        newUser.setId(newId);
        MATCHER.assertMatch(userNoRoles, newUser);
        MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void updateDeleteRoles() {
        User userNoRoles = getUpdated();
        userNoRoles.setRoles(Collections.emptyList());
        service.update(userNoRoles);
        User expected = getUpdated();
        expected.setRoles(Collections.emptyList());
        MATCHER.assertMatch(service.get(USER_ID), expected);
    }

    @Test
    public void deleteNoRoles() {
        User userNoRoles = getNew();
        userNoRoles.setRoles(Collections.emptyList());
        userNoRoles = service.create(userNoRoles);
        int newId = userNoRoles.id();
        User newUser = getNew();
        newUser.setRoles(Collections.emptyList());
        newUser.setId(newId);
        MATCHER.assertMatch(userNoRoles, newUser);
        MATCHER.assertMatch(service.get(newId), newUser);
        service.delete(newId);
        assertThrows(NotFoundException.class, () -> service.get(newId));
    }
}