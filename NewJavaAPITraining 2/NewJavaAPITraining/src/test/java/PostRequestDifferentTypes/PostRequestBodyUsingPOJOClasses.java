package PostRequestDifferentTypes;

import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestBodyUsingPOJOClasses {

    @Test(priority = 1)
    void testPostusingPOJOClass(){

        Pojo_Postrequest pj = new Pojo_Postrequest();

        pj.setName("Scott");
        pj.setLocation("France");
        pj.setPhone("1232342");

        String courseArr[] = {"C" , "C++"};
        pj.setCourses(courseArr);

        given()
                .contentType("application/json")
                .body(pj)
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
