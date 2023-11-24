package PostRequestDifferentTypes;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestBodyUsingExternalJSONFile {

    @Test(priority = 1)
    void testPostusingExternalJSONFile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr =new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);

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
