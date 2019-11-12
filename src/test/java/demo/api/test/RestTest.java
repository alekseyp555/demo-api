package demo.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestTest {
    final static String ROOT_URI = "http://localhost:3000";

    @Test
    public void getUsersList() {
        given().when().get(ROOT_URI).then()
                .statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("result[9].name", equalTo("Bob Dowson"))
                .and().header("Server", "WEBrick/1.3.1 (Ruby/2.3.3/2016-11-21)");
    }

    @Test
    public void getUsersListWithCount() {
        int count = 5;
        given().when().get(ROOT_URI + "/index?offset=0&count=" + count).then()
                .statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("result[0].name", equalTo("David Bush"))
                .and().header("Server", "WEBrick/1.3.1 (Ruby/2.3.3/2016-11-21)");
    }

    @Test
    public void getNameById() {
        int id = 119041;
        given().when().get(ROOT_URI + "/get?id= " + id).then()
                .statusCode(200)
                .and().header("Server", "WEBrick/1.3.1 (Ruby/2.3.3/2016-11-21)");
    }

    @Test
    public void getNameByWrongId() {
        int id = 111111;
        given().when().get(ROOT_URI + "/get?id=" + id).then()
                .statusCode(404)
                .and().header("Server", "WEBrick/1.3.1 (Ruby/2.3.3/2016-11-21)");
    }

    @Test(enabled = false)
    public void resetUsersListId() {
        given().when().get(ROOT_URI + "/reset").then().statusCode(200)
                .and().header("Server", "WEBrick/1.3.1 (Ruby/2.3.3/2016-11-21)");
    }
}




