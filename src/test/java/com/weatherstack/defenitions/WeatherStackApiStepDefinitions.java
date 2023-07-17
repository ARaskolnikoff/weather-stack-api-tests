package com.weatherstack.defenitions;

import config.Properties;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс определения шагов для тестирования API погоды с использованием фреймворка Cucumber.
 *
 * @author Петр Белинский
 */
public class WeatherStackApiStepDefinitions {

    private static final String BASE_URL = Properties.testProperties.weatherStackApiUrl();
    private static final String API_KEY = Properties.testProperties.weatherStackApiKey();
    private Response response;

    /**
     * Задает ключ API для WeatherStack.
     */
    @Дано("Имеется ключ API к WeatherStack")
    public void задатьКлючAPI() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = RestAssured.given().queryParam("access_key", API_KEY);
    }

    /**
     * Отправляет запрос текущей погоды для указанного города.
     *
     * @param city город, для которого отправляется запрос текущей погоды
     */
    @Когда("Отправляется запрос текущей погоды для города {string}")
    public void отправкаЗапросаТекущейПогоды(String city) {
        response = RestAssured.given()
                .queryParam("query", city)
                .get("current");
    }

    /**
     * Проверяет, что ответ содержит корректные значения для указанных данных местоположения.
     *
     * @param country     ожидаемая страна
     * @param lat         ожидаемая широта
     * @param lon         ожидаемая долгота
     * @param timezoneId  ожидаемый часовой пояс
     * @param utcOffset   ожидаемое смещение по UTC
     */
    @То("Ответ должен содержать корректные значения для: {string}, {string}, {string}, {string}, {string}")
    public void проверкаЗначенийОтвета(String country, String lat, String lon, String timezoneId, String utcOffset) {
        assertAll(
                "Проверка данных местоположения",
                () -> assertEquals(country, response.jsonPath()
                        .getString("location.country"),
                        "Полученное значение страны не соответствует ожидаемому!"),
                () -> assertEquals(lat, response.jsonPath()
                        .getString("location.lat"),
                        "Полученное значение широты не соответствует ожидаемому!"),
                () -> assertEquals(lon, response.jsonPath()
                        .getString("location.lon"),
                        "Полученное значение долготы не соответствует ожидаемому!"),
                () -> assertEquals(timezoneId, response.jsonPath()
                        .getString("location.timezone_id"),
                        "Полученное значение часового пояса не соответствует ожидаемому!"),
                () -> assertEquals(utcOffset, response.jsonPath()
                        .getString("location.utc_offset"),
                        "Полученное значение смещения по UTC не соответствует ожидаемому!")
        );
    }

    /**
     * Отправляет неверный запрос к указанному эндпоинту.
     *
     * @param endpoint эндпоинт, к которому отправляется неверный запрос
     */
    @Когда("Отправляется неверный запрос {string}")
    public void отправкаНеверногоЗапроса(String endpoint) {
        response = RestAssured.given()
                .queryParam("query", "New York")
                .queryParam("forecast_days", 1)
                .get(endpoint);
    }

    /**
     * Проверяет, что ответ содержит указанное сообщение.
     *
     * @param responseMessage ожидаемое сообщение в ответе
     */
    @То("Ответ должен содержать {string}")
    public void проверкаСодержимогоОтвета(String responseMessage) {
        assertThat("Полученный ответ не содержит ожидаемого сообщения!",
                response.getBody().asString(), containsString(responseMessage));
    }
}