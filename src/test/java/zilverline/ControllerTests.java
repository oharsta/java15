package zilverline;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void before() {
        RestAssured.port = port;
    }

    @Test
    void conf() {
        UserConfig userConfig = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/conf")
                .as(UserConfig.class);
        assertEquals(2, userConfig.getUsers().size());
    }

    @Test
    void token() {
        TokenUsers tokenUsers = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/token")
                .as(TokenUsers.class);
        assertEquals(2, tokenUsers.getUsers().size());
        assertTrue(tokenUsers.isEnabled());
    }

    @Test
    void persons() {
        List<Person> persons = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/persons")
                .as(new TypeRef<>() {
                });
        assertEquals(2, persons.size());
    }

    @Test
    void health() {
        given()
                .when()
                .get("/actuator/health")
                .then()
                .body("status", equalTo("UP"));
    }

    @Test
    void notFound() {
        given()
                .when()
                .get("/nope")
                .then()
                .statusCode(404);
    }

}
