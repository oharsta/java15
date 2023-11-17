package zilverline;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.PI;
import static java.time.DayOfWeek.SATURDAY;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.*;

public class LanguageFeaturesTests {

    @Test
    void java8functional() {
        List<String> strings = Arrays.asList("1", "2", "3", "3");
        Set<Integer> integerSet = strings.stream().map(s2 -> Integer.valueOf(s2)).collect(Collectors.toSet());
        assertEquals(3, integerSet.size());

        String s1 = strings.stream().filter(s -> s.equals("2")).findFirst().orElseThrow(() -> new IllegalArgumentException());
        assertEquals("2", s1);

        boolean b = strings.stream().anyMatch(s -> Integer.parseInt(s) > 2);
        assertTrue(b);

        boolean c = strings.stream().allMatch(s -> Integer.parseInt(s) > 2);
        assertFalse(c);

        //static import
        Map<String, Integer> map = new HashSet<>(strings).stream()
                .collect(toMap(s -> s, Integer::parseInt));
        assertEquals(3, map.size());
    }

    @Test
    void optionalMethods() {
        Optional<String> stringOptional = Optional.of("1");
        Optional<Integer> optionalInteger = stringOptional.map(s -> Integer.parseInt(s));
        optionalInteger.ifPresent(i -> assertEquals(1, i));
        optionalInteger.ifPresentOrElse(i -> System.out.println(i), () -> System.out.println("nope"));
    }

    //functional interface - can only have one method
    interface Execute {
        boolean doIt();
    }

    @Test
    void lambda() {
        Execute e = () -> true;
        boolean b = e.doIt();
        assertTrue(b);
    }

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
    void records() {
        var p = new Person("jd", 31);
        assertEquals(31, p.age());
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
            case THURSDAY -> 0;
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
        assertEquals(8, numLetters);
    }

    @Test
    void regExpJava9ResultsGroup() {
        Pattern pattern = Pattern.compile("([a-z0-9_.-]+@[a-z0-9_.-]+[a-z])");
        String emails = "Emails jdoe@test.com, mdoe@example.com, and the last one team@qwerty.com";
        Matcher matcher = pattern.matcher(emails);
        Set<String> results = matcher.results().map(MatchResult::group).collect(Collectors.toSet());

        assertEquals(3, results.size());
    }

    @Test
    void listCollectors() {
        var source = List.of("List", "Map", "Set", "Tree");
        Map<Integer, List<String>> result = source.stream().collect(Collectors.groupingBy(String::length));
        assertEquals("{3=[Map, Set], 4=[List, Tree]}", result.toString());
    }

    interface TestInterface {
        private boolean internal() {
            return true;
        }

        default boolean def() {
            return internal() && implementMe();
        }

        boolean implementMe();
    }

    @Test
    void interfaceMethods() {
        var t = new TestInterface() {
            @Override
            public boolean implementMe() {
                return false;
            }
        };
        assertFalse(t.def());
    }

    @Test
    void newStringMethods() {
        assertTrue(" ".isBlank());
        assertEquals("testtest", "test".repeat(2));

    }

    @Test
    void testRecordPatters() {
        record Name(String fName, String lName, Integer age) {
        }
        ;
        Name host = new Name("John", "Doe", 37);
        String name = switch (host) {
            case Name(var fName, var lName, var mName) -> lName + ", " + fName + " " + mName;
        };
        assertEquals(name, STR. "\{ host.lName }, \{ host.fName } \{ host.age }" );
    }

    @Test
    void testSwitch() {
        interface Shape {
            double area();
        }
        record Circle(double diameter) implements Shape {
            @Override
            public double area() {
                return PI * Circle.this.diameter * Circle.this.diameter;
            }
        }
        record Square(double side) implements Shape {
            @Override
            public double area() {
                return Square.this.side * Square.this.side;
            }
        }
        Shape shape = new Circle(10);
        String message = switch (shape) {
            case Circle c -> "A circle with area of " + c.area();
            case Square s -> "A square with area of " + s.area();
            default -> "Unknown shape";
        };
        assertEquals("A circle with area of " + shape.area(), message);
    }
}
