package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WsWelcomePage {
    private final SelenideElement registerButton =  $("a.ico-register");
    private final SelenideElement loginLink =  $("a.ico-login");

    @Step("Переход на страницу регистрации")
    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    @Step("Переход на страницу входа")
    public WSLoginPage openLogin() {
        loginLink.click();
        return new WSLoginPage();
    }
}
