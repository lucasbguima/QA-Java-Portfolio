package com.portfolio.product_management.api.tests;

import com.portfolio.product_management.api.specs.ProductApiSpec;
import com.portfolio.product_management.models.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductApiTest {

    @LocalServerPort
    private int port;

    private static Long createdProductId;

    @BeforeEach
    public void setup() {
        RestAssured.requestSpecification = ProductApiSpec.getRequestSpec(port);
    }

    @Test
    @Order(1)
    @DisplayName("Should create a new product via POST")
    public void testCreateProduct() {
        Product newProduct = new Product(null, "Gaming Mouse", 89.99, "RGB Gaming Mouse", "https://via.placeholder.com/150");

        Number idNumber = given()
                .body(newProduct)
        .when()
                .post()
        .then()
                .spec(ProductApiSpec.getResponseSpec(201))
                .body("name", equalTo("Gaming Mouse"))
                .body("price", equalTo(89.99f))
                .extract()
                .path("id");

        createdProductId = idNumber.longValue();
        Assertions.assertNotNull(createdProductId);
    }

    @Test
    @Order(2)
    @DisplayName("Should retrieve all products via GET")
    public void testGetAllProducts() {
        given()
        .when()
                .get()
        .then()
                .spec(ProductApiSpec.getResponseSpec(200))
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    @DisplayName("Should retrieve a product by ID via GET")
    public void testGetProductById() {
        given()
                .pathParam("id", createdProductId)
        .when()
                .get("/{id}")
        .then()
                .spec(ProductApiSpec.getResponseSpec(200))
                .body("id", equalTo(createdProductId.intValue()))
                .body("name", equalTo("Gaming Mouse"));
    }

    @Test
    @Order(4)
    @DisplayName("Should update an existing product via PUT")
    public void testUpdateProduct() {
        Product updatedProduct = new Product(createdProductId, "Updated Mouse", 99.99, "Updated Description", "https://via.placeholder.com/150");

        given()
                .pathParam("id", createdProductId)
                .body(updatedProduct)
        .when()
                .put("/{id}")
        .then()
                .spec(ProductApiSpec.getResponseSpec(200))
                .body("name", equalTo("Updated Mouse"))
                .body("price", equalTo(99.99f));
    }

    @Test
    @Order(5)
    @DisplayName("Should delete a product via DELETE")
    public void testDeleteProduct() {
        given()
                .pathParam("id", createdProductId)
        .when()
                .delete("/{id}")
        .then()
                .statusCode(204);

        given()
                .pathParam("id", createdProductId)
        .when()
                .get("/{id}")
        .then()
                .statusCode(404);
    }
}