package ru.javawebinar.topjava.service.jdbc;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void createNoRoles() {
        User userNoRoles = getNew();
        userNoRoles.setRoles(Collections.emptyList());
        service.create(userNoRoles);
        int newId = userNoRoles.id();
        User newUser = getNew();
        newUser.setRoles(Collections.emptyList());
        newUser.setId(newId);
        MATCHER.assertMatch(userNoRoles, newUser);
    }

    @Test
    public void updateDeleteRoles() {
        User userNoRoles = getUpdated();
        userNoRoles.setRoles(Collections.emptyList());
        service.update(userNoRoles);
        MATCHER.assertMatch(service.get(USER_ID), userNoRoles);
    }

    @Test
    public void deleteNoRoles() {
        User userNoRoles = getNew();
        userNoRoles.setRoles(Collections.emptyList());
        service.create(userNoRoles);
        int newId = userNoRoles.id();
        service.delete(newId);
        assertThrows(NotFoundException.class, () -> service.get(newId));
    }
}