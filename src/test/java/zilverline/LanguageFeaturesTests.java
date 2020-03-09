package zilverline;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
}
