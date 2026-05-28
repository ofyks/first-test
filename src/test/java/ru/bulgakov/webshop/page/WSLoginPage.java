package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Проверить, что страница входа открыта")
    public WSLoginPage checkLoginPageOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    @Step("Ввести email: {email}")
    public WSLoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public WSLoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Поставить галочку 'Remember me'")
    public WSLoginPage checkRememberMe() {
        RememberMeInput.click();
        return this;
    }

    @Step("Нажать кнопку 'Log in'")
    public WSLoginPage submitLogin() {
        loginButton.click();
        return this;
    }

    @Step("Проверить, что пользователь вошел под email: {email}")
    public WSLoginPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}

