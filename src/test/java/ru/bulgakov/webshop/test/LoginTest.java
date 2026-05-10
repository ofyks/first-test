package ru.bulgakov.webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.page.WsRegistrationPage;
import ru.bulgakov.webshop.page.WsWelcomePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class LoginTest {
    private static final Faker faker = new Faker();
    private String email;
    private String password;


    @BeforeEach
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


//        $("a.ico-login").click();
//        $("div.page-title h1").shouldHave(text("Welcome, Please Sign In!"));
//        $("input#Email").setValue(email);
//        $("input#Password").setValue(password);
//        $("input#RememberMe").click();
//        $("input#login-button").click();
//        $$("div.header-links ul li a").get(0).shouldHave(text(email));