package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WSLoginPage {
    private final SelenideElement pageTitle =  $("div.page-title h1");
    private final SelenideElement emailInput =  $("input#Email");
    private final SelenideElement passwordInput =  $("input#Password");
    private final SelenideElement RememberMeInput =  $("input#RememberMe");
    private final SelenideElement loginButton =  $("input.login-button");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public WSLoginPage checkLoginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    public WSLoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public WSLoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public WSLoginPage checkRememberMe() {
        RememberMeInput.click();
        return this;
    }

    public WSLoginPage submitLogin() {
        loginButton.click();
        return this;
    }

    public WSLoginPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}

