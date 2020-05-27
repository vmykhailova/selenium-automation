package com.hillel.auto.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class RegistrationTest extends BaseApiTest {
private String token;
    @Test
    public void registrationUsersTest(){
        RestAssured
                .given()
                    .body("{\n" +
                            "\"user\":{\n" +
                            "\"username\": \"vikkimix6\",\n" +
                            "\"email\": \"vikkimix6@gmail.com\",\n" +
                            "\"password\": \"qwerty123\"\n" +
                            "}\n" +
                            "}")
                .when()
                    .post("/users")
                .then()
                    .statusCode(200)
                    .body("user.email", equalTo("vikkimix6@gmail.com"))
                    .body("user.username", equalTo("vikkimix6"))
                    .body("user.token", not(emptyOrNullString()))
                .log().all();
    }

    @Test
    public void LoginUserTest(){
        RestAssured
                .given()
                .body("{\n" +
                        "\"user\":{\n" +
                        "\"email\": \"realapp@mail.com\",\n" +
                        "\"password\": \"qwerty123\"\n" +
                        "}\n" +
                        "}")
                .when()
                .post("/users/login")
                .then()
                .statusCode(200)
                .body("user.email", equalTo("realapp@mail.com"))
                .body("user.username", equalTo("realapp"))
                .body("user.token", not(emptyOrNullString()))
                .log().all();
    }

    @Test
    public void createArticleTest() {
        RestAssured
                .given()
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OTUxMjMsInVzZXJuYW1lIjoicmVhbGFwcCIsImV4cCI6MTU5NTc4ODYxOX0.8eYspfEw4kLI25qqYSto7V_NNdI7wLLJq2FkdSlumjQ")
                .body("{\n" +
                        "  \"article\": {\n" +
                        "    \"title\": \"vikkimix\",\n" +
                        "    \"description\": \"This is test api article\",\n" +
                        "    \"body\": \"sdjvbsjdbv bjbsdkhjvbkjshdv sdjhbjsdhbfkjhdsf\",\n" +
                        "    \"tagList\": [\n" +
                        "      \"string test\"\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}\n")
                .when()
                .post("/articles")
                .then()
                .statusCode(200)
                .body("article.title", equalTo("vikkimix"))
                .body("article.description", equalTo("This is test api article"))
                .log().all();
    }
}
