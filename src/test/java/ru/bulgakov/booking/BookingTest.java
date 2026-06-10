package ru.bulgakov.booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.booking.dto.AuthRequest;
import ru.bulgakov.booking.dto.AuthResponse;
import ru.bulgakov.booking.dto.BookingDTO;
import ru.bulgakov.booking.dto.CreateBookingResponse;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTest {
    private static final String BOOKING_URL = "https://restful-booker.herokuapp.com";

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
    }

    @Test
    void authTest() {
        AuthResponse resp = given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest("admin", "password123"))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200)
                .extract().as(AuthResponse.class);

        assertThat(resp.getToken()).isNotNull();
    }

    @Test
    @DisplayName("Тест - 1: Неверный пароль")
    void authWrongPassword() {
        given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest("admin", "password777"))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        // Должна прийти 403
    }

    @Test
    @DisplayName("Тест - 2: Неверный логин")
    void authWrongLogin() {
        given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest("bibia", "password123"))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        // Должна прийти 403
    }

    @Test
    @DisplayName("Тест - 3: Пустой пароль")
    void authEmptyPassword() {
        given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest("admin", ""))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        //403
    }

    @Test
    @DisplayName("Тест - 4: Пустой пароль")
    void authEmptyLogin() {
        given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest("", "password123"))
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        //403
    }

    @Test
    @DisplayName("Тест - 5: Пустое body")
    void authEmptyBody() {
        given()
                .contentType(ContentType.JSON)
                .body(new AuthRequest())
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        //403
    }

    @Test
    @DisplayName("Без body")
    void authWithoutBody() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post(BOOKING_URL + "/auth")
                .then()
                .statusCode(200);
        //403
    }

    @Test
    @DisplayName("Тест - 1: Без firstname")
    void createBookingWithoutFirstname() {
        BookingDTO request = BookingDTO.builder()
                .lastname("Brown")
                .totalprice(1000)
                .depositpaid(false)
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .extract().response();

        assertThat(response.getStatusCode())
                .as("При отсутствии firstname сервер должен вернуть ошибку")
                .isEqualTo(500);
        //400 Bad Request должна быть
    }

    @Test
    @DisplayName("Тест - 2: Без lastname")
    void createBookingWithoutLastname() {
        BookingDTO request = BookingDTO.builder()
                .firstname("James")
                .totalprice(1000)
                .depositpaid(false)
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .extract().response();

        assertThat(response.getStatusCode())
                .as("При отсутствии lastname сервер должен вернуть ошибку")
                .isEqualTo(500);
        //400 Bad Request
    }

    @Test
    @DisplayName("Тест - 3: Отрицательная цена")
    void createBookingWithNegativePrice() {
        BookingDTO request = BookingDTO.builder()
                .firstname("James")
                .lastname("Brown")
                .totalprice(-500)
                .depositpaid(false)
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .build();

        Response resp = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .extract().response();

        assertThat(resp.getStatusCode()).isEqualTo(200);

        CreateBookingResponse actual = resp.as(CreateBookingResponse.class);

        assertThat(actual.getBookingid()).isNotNull();
        assertThat(actual.getBooking().getTotalprice()).isEqualTo(-500);
    }

    @Test
    void createBookingTest() {
        CreateBookingResponse res = given()
                .contentType(ContentType.JSON)
                .body(buildCreateBookingRequest())
                .when()
                .post(BOOKING_URL + "/booking")
                .then()
                .statusCode(200)
                .extract().as(CreateBookingResponse.class);

        assertThat(res.getBookingid()).isNotNull();
        assertThat(res.getBooking().getTotalprice()).isEqualTo(1000);
        assertThat(res.getBooking().getBookingdates().getCheckin()).isEqualTo("2026-01-01");
        assertThat(res.getBooking().getDepositpaid()).isFalse();
    }

    private static BookingDTO buildCreateBookingRequest() {
        return BookingDTO.builder()
                .firstname("Jiam")
                .lastname("Brown")
                .totalprice(1000)
                .depositpaid(false)
                .bookingdates(BookingDTO.BookingDates.builder()
                        .checkin("2026-01-01")
                        .checkout("2027-01-01")
                        .build())
                .additionalneeds("Newspaper")
                .build();
    }
}
