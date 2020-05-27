package com.hillel.auto.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://conduit.productionready.io";
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder().setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON).log(LogDetail.ALL)
//                        .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OTUxMjMsInVzZXJuYW1lIjoicmVhbGFwcCIsImV4cCI6MTU5NTcwODYyOX0.tNu_9K3zTu0tj7FU8uadXIxe-EdqNYzRFr5_Ot6sjA4" )
                        .build();
        RequestSpecification given = RestAssured.given();
    }
}