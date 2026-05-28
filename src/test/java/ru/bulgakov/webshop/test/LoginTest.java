package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.page.WsRegistrationPage;
import ru.bulgakov.webshop.page.WsWelcomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

@Epic("Авторизация и Регистрация")
@Feature("Вход в систему")
public class LoginTest extends TestBase {
    private static final Faker faker = new Faker();
    private String email;
    private String password;


    @BeforeEach
    @Step("Подготовка: Регистрация нового пользователя перед тестом входа")
    void beforeEach() {
        email = faker.internet().emailAddress();
        password = faker.harryPotter().character() + faker.number().positive();

        open(WEB_SHOP_REGISTRATION, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        password)
                .checkUserLoggedIn(email);


        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Test
    @DisplayName("Успешный вход в систему с валидными учетными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Кирюха")
    @Link(name = "LOGIN-001", url = "https://demowebshop.tricentis.com/login")
    void successLoginTest() {
        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openLogin()
                .checkLoginPageOpened()
                .setEmail(email)
                .setPassword(password)
                .checkRememberMe()
                .submitLogin()
                .checkUserLoggedIn(email);
    }
}
