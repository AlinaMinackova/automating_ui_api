package api.tests;

import api.models.products.ProductRequest;
import api.models.products.ProductResponse;
import api.utils.ProductCreate;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.spec.Specification.requestSpec;
import static api.spec.Specification.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Tag("api")
public class ProductResponseTests{

    @Test
    @DisplayName("Получение продуктов")
    public void getProducts() {
        List<ProductResponse> getAllProductResponses = step("Получение продуктов", () ->
                given(requestSpec)
                        .basePath("/products")
                        .when()
                        .get()
                        .then()
                        .spec(responseSpec))
                .statusCode(200)
                .extract().body().jsonPath().getList("", ProductResponse.class);
        step("Проверка получения продуктов", () ->
                getAllProductResponses.forEach(productResponse -> {
                    System.out.println(productResponse.getRating());
                }));

    }

    @Test
    @DisplayName("Добавление продукта")
    public void createProduct() {
        ProductRequest productRequest = step("Создание продукта", ProductCreate::randomProduct);

        ProductResponse product = step("Добавление продукта", () ->
                given(requestSpec)
                        .basePath("/products")
                        .when()
                        .body(productRequest)
                        .post()
                        .then()
                        .spec(responseSpec))
                .statusCode(201)
                .extract().as(ProductResponse.class);
        step("Проверка добавление продукта", () ->
                Assertions.assertEquals(productRequest.getTitle(), product.getTitle()));

    }

    @Test
    @DisplayName("Получение продукта")
    public void getSingleProduct() {
        ProductResponse productResponse = step("Получение продукта", () ->
                given(requestSpec)
                        .when()
                        .pathParam("id", "1")
                        .basePath("/products/{id}")
                        .get()
                        .then()
                        .spec(responseSpec))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/ProductSingle.json"))
                .extract().as(ProductResponse.class);
        step("Проверка получения продукта", () ->
                System.out.println(productResponse));
    }

    @Test
    @DisplayName("Обновление продукта")
    public void updateProduct() {
        ProductRequest productRequest = step("Содание продукта", () ->
                ProductRequest.builder()
                        .price(100.0)
                        .build());

        ProductResponse product = step("Обновление продукта", () ->
                given(requestSpec)
                        .pathParam("id", "1")
                        .basePath("/products/{id}")
                        .when()
                        .body(productRequest)
                        .put()
                        .then()
                        .spec(responseSpec))
                .statusCode(200)
                .extract().as(ProductResponse.class);
        step("Проверка обновления продукта", () ->
                Assertions.assertEquals(productRequest.getPrice(), product.getPrice()));

    }

    @Test
    @DisplayName("Удаление продукта")
    public void deleteProduct() {
        step("Удаление продукта", () ->
                given(requestSpec)
                        .pathParam("id", "1")
                        .basePath("/products/{id}")
                        .when()
                        .delete()
                        .then()
                        .spec(responseSpec))
                .statusCode(200);


    }
}
