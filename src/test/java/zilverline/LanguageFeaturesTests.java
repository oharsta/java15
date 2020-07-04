package zilverline;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.time.DayOfWeek.SATURDAY;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageFeaturesTests {

    @Test
    void listOf() {
        List<Integer> ints = List.of(1, 2, 3);
        assertEquals(3, ints.size());
    }

    @Test
    void mapOf() {
        var map = Map.of("nice", 1, "wtf", 2);
        assertEquals(2, map.size());
    }

    @Test
    void varDeclaration() {
        var greeting = "Hello World!";
        assertTrue(greeting instanceof String);
    }

    @Test
    void multiLine() {
        var s = """
                very nice
                line breaks
                """;
        assertEquals(2, s.lines().count());
    }

    @Test
    void person() {
        var p = new Person("jd", 31);
        assertEquals(31,p.age());
    }

    @Test
    void patternInstanceOf() {
        Object obj = "1234567";
        if (obj instanceof String str && str.length() > 5) {
            assertEquals(7, str.length());
        }
    }

    @Test
    void switchReturnValue() {
        var day = SATURDAY;
        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
        };
        assertEquals(8, numLetters);
    }
}
