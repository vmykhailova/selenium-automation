package com.hillel.auto.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ArticlesApiTest extends BaseApiTest {
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