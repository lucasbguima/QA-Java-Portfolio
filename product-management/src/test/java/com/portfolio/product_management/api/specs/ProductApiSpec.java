package com.portfolio.product_management.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ProductApiSpec {

    public static RequestSpecification getRequestSpec(int port) {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(port)
                .setBasePath("/api/products")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}