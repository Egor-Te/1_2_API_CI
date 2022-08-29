package ru.netology.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }


    @Test
    void shouldContetType() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("demo/accounts")
                .then()
                //.header("Content-Type", "application/json; charset=UTF-8")
                .contentType(ContentType.JSON);
    }

    @Test
    void shouldBody3() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("demo/accounts")
                .then()
                .body("", hasSize(3));
    }

    @Test
    void shouldCurrencyUSD() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("demo/accounts")
                .then()
                .body("[1].currency", equalTo("RUB"));
    }

    @Test
    void shouldBalancePositive() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("demo/accounts")
                .then()
                .body("[2].balance", greaterThanOrEqualTo(0));
    }

    @Test
    void test() {
        given()
                .then()
                .statusCode(200)
                // static import для JsonSchemaValidator.matchesJsonSchemaInClasspath
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }

    @Test
    void shouldCurrencyRub() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[0].currency", equalTo("RUR"));

    }

    @Test
    void shouldCurrencyUsd() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[2].currency", equalTo("USD"));

    }
}


