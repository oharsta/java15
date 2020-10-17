package zilverline;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTests {

    @LocalServerPort
    private int port;

    @Before
    public void before() throws IOException {
        RestAssured.port = port;
    }

    @Test
    public void conf() {
        UserConfig userConfig = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/conf")
                .as(UserConfig.class);
        assertEquals(2, userConfig.getUsers().size());
    }

    @Test
    public void token() {
        TokenUsers tokenUsers = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/token")
                .as(TokenUsers.class);
        assertEquals(2, tokenUsers.getUsers().size());
        assertTrue(tokenUsers.isEnabled());
    }

    @Test
    public void persons() {
        List<Person> persons = given()
                .when()
                .contentType(ContentType.JSON)
                .get("/persons")
                .as(new TypeRef<>() {
                });
        assertEquals(2, persons .size());
    }}
