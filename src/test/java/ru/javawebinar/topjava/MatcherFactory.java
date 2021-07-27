package ru.javawebinar.topjava;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Factory for creating test matchers.
 * <p>
 * Comparing actual and expected objects via AssertJ
 * Support converting json MvcResult to objects for comparation.
 */
public class MatcherFactory<T> {
    private final Class<T> clazz;
    private final BiConsumer<T, T> biConsumerAssert;
    private final BiConsumer<Iterable<T>, Iterable<T>> iterableBiConsumerAssert;

    private MatcherFactory(Class<T> clazz,
                           BiConsumer<T, T> biConsumerAssert,
                           BiConsumer<Iterable<T>, Iterable<T>> iterableBiConsumerAssert) {
        this.clazz = clazz;
        this.biConsumerAssert = biConsumerAssert;
        this.iterableBiConsumerAssert = iterableBiConsumerAssert;
    }

    private static <T> MatcherFactory<T> createMatcher(Class<T> clazz,
                                                       BiConsumer<T, T> biConsumerAssert,
                                                       BiConsumer<Iterable<T>, Iterable<T>> iterableBiConsumerAssert) {
        return new MatcherFactory<>(clazz, biConsumerAssert, iterableBiConsumerAssert);
    }

    public static <T> MatcherFactory<T> usingIgnoringFieldsComparator(Class<T> clazz, String... fieldsToIgnore) {
        return createMatcher(clazz,
                (actual, expected) -> assertThat(actual).usingRecursiveComparison().ignoringFields(fieldsToIgnore).isEqualTo(expected),
                (actual, expected) -> assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected));
    }

    public static <T> MatcherFactory<T> usingEqualsComparator(Class<T> clazz) {
        return createMatcher(clazz,
                (actual, expected) -> assertThat(actual).isEqualTo(expected),
                (actual, expected) -> assertThat(actual).isEqualTo(expected));
    }

    public void assertMatch(T actual, T expected) {
        biConsumerAssert.accept(actual, expected);
    }

    @SafeVarargs
    public final void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        iterableBiConsumerAssert.accept(actual, expected);
    }

    public ResultMatcher contentJson(T expected) {
        return result -> assertMatch(JsonUtil.readValue(getContent(result), clazz), expected);
    }

    @SafeVarargs
    public final ResultMatcher contentJson(T... expected) {
        return contentJson(List.of(expected));
    }

    public ResultMatcher contentJson(Iterable<T> expected) {
        return result -> assertMatch(JsonUtil.readValues(getContent(result), clazz), expected);
    }

    public T readFromJson(ResultActions action) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action.andReturn()), clazz);
    }

    private static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }
}
