import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class httprequests{

    int id;

        @Test(priority=1)
        public void getUserslist()
        {
                    given()

                    .when()
                        .get("https://reqres.in/api/users?page=2")

                    .then()
                        .statusCode(200)
                        .body("page", equalTo(2))
                        .log().all();
        }

        @Test(priority =2)
        void createUser()
        {
            HashMap hm = new HashMap();
            hm.put("name", "Ram");
            hm.put("job", "trainer");

            id = given()
                    .contentType("application/json")
                    .body(hm)
            .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");
            /*.then()
                    .statusCode(201);*/
                    //.log().all();
        }

    @Test(priority =3, dependsOnMethods = "createUser")
    void updateUser()
    {
        HashMap hm = new HashMap();
        hm.put("name", "Senthil");
        hm.put("job", "ITConsultant");

        given()
                .contentType("application/json")
                .body(hm)
        .when()
                .put("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void deleteuser()
    {
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }
}
