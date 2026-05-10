package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WsWelcomePage {
    private final SelenideElement registerButton =  $("a.ico-register");
    private final SelenideElement loginLink =  $("a.ico-login");

    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    public WSLoginPage openLogin() {
        loginLink.click();
        return new WSLoginPage();
    }
}
