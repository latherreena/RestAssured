package PostRequestDifferentTypes;

import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestBodyUsingOrgJSONLib {

    @Test(priority = 1)
    void testPostusingORGJSONLib(){

        JSONObject jo = new JSONObject();

        jo.put("name","Scott");
        jo.put("location" ,"France");
        jo.put("phone", "1232342");

        String courseArr[] = {"C" , "C++"};
        jo.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(jo.toString())
        .when()
                .post("http://localhost:3000/students")
        .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("1232342"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    @Test(priority = 2)
    void DeleteRequest()
    {
        given()
        .when()
                .delete("http://localhost:3000/students/4")
        .then()
                .statusCode(200)
                .log().all();
    }

}
