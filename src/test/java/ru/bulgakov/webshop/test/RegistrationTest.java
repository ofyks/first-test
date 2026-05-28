package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.page.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;

@Epic("Авторизация и Регистрация")
@Feature("Регистрация нового пользователя")

public class RegistrationTest extends TestBase {
    private static final Faker faker = new Faker();

    @Test
    @DisplayName("Успешная регистрация нового пользователя с валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Кирюха")
    @Link(name = "REG-1", url = "https://demowebshop.tricentis.com/register")
    void registrationTest() {
        String email = faker.internet().emailAddress();
        String password = faker.harryPotter().character() + faker.number().positive();

        open("https://demowebshop.tricentis.com/", WsWelcomePage.class)
                .openRegistration()
                .verifyRegistrationOpen()
                .selectMaleGendor()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(password)
                .submitRegistrationButton()
                .checkRegistrationCompleted()
                .checkUserLoggedIn(email);

    }
}
