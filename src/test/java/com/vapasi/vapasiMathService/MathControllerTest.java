package com.vapasi.vapasiMathService;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "http://localhost:8081")
public class MathControllerTest {

    @LocalServerPort
    int port;

    @Test
    public void ShouldBeAbleToAddIntegers(){
        RestAssured.baseURI = "http://localhost:"+port;

        String requestBody = "{\"o\":1, \"t\":2, \"e\":3}";
        Double expectedSum = 6.0;

        Response response = given()
                .header(new Header("Content-Type", "application/json"))
                .request()
                .body(requestBody)
                .post("/add")
                .then()
                .statusCode(200)
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();

        Double actualSum = Double.valueOf(jsonResponse.get("sum").toString());
        Assertions.assertEquals(expectedSum, actualSum);

    }

}
