package api.tests;

import api.models.carts.CardRequest;
import api.models.carts.CardResponse;
import api.models.carts.CardResponseForList;
import api.utils.CartCreate;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static api.spec.Specification.requestSpec;
import static api.spec.Specification.responseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

@Tag("api")
public class CardsTests {

    @Test
    @DisplayName("Получение списка карт")
    public void getCards() {
        List<CardResponseForList> getAllCartsResponses = step("Получение списка карт", () ->
                given(requestSpec)
                        .basePath("/carts")
                        .when()
                        .get()
                        .then()
                        .spec(responseSpec))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/CartsList.json"))
                .extract().body().jsonPath().getList("", CardResponseForList.class);
        step("Проверка списка карт", () -> {
            System.out.println(getAllCartsResponses.size());
        });
    }

    @Test
    @DisplayName("Получение карты по id")
    public void getSingleCards() {
        step("Получение карты по id", () ->
                given(requestSpec)
                        .pathParam("id", "1")
                        .basePath("/carts/{id}")
                        .when()
                        .get()
                        .then()
                        .spec(responseSpec))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemes/cartSingle.json"))
                .extract().body().as(CardResponseForList.class);;
    }

    @Test
    @DisplayName("Создание карты")
    public void postCard() {
        CardRequest cardRequest = step("Создание карты", CartCreate::createRandom);

        CardResponse addProduct = step("Добавление карты", () ->
                given(requestSpec)
                        .basePath("/carts")
                        .when()
                        .body(cardRequest)
                        .post()
                        .then()
                        .spec(responseSpec))
                .statusCode(201)
                .extract().response().getBody().as(CardResponse.class);

        step("Проверка списка продуктов", () ->
                Assertions.assertEquals(cardRequest.products.size(), addProduct.getProducts().size()));

    }

    @Test
    @DisplayName("Обновление карты")
    public void updateCard() {
        CardRequest cardRequest = step("Создание карты", CartCreate::createRandom);
        CardResponse singleCard2 = (step("Обновление карты", () ->
                given(requestSpec)
                        .pathParam("id", "1")
                        .basePath("/carts/{id}")
                        .body(cardRequest)
                        .when()
                        .put()
                        .then()
                        .spec(responseSpec))
                .statusCode(200).assertThat()
                .extract().response().getBody().as(CardResponse.class));
        step("Проверка id полученной карты", () ->
                Assertions.assertEquals(cardRequest.getUserId(), singleCard2.getUserId()));
    }

    @Test
    @DisplayName("Удаление карты")
    public void deleteCard() {
        step("Удаление карты", () ->
                given(requestSpec)
                        .pathParam("id", "1")
                        .basePath("/carts/{id}")
                        .when()
                        .get()
                        .then()
                        .spec(responseSpec))
                .statusCode(200);
    }
}
