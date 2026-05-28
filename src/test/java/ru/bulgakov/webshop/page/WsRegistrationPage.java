package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsRegistrationPage {
    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement maleGenderRadio = $("input#gender-male");
    private final SelenideElement firstNameInput = $("input#FirstName");
    private final SelenideElement lastNameInput = $("input#LastName");
    private final SelenideElement emailInput = $("input#Email");
    private final SelenideElement passwordInput = $("input#Password");
    private final SelenideElement passwordConfirmInput = $("input#ConfirmPassword");
    private final SelenideElement registrationInput = $("input#register-button");
    private final SelenideElement resultText = $("div.result");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    @Step("Зарегистрировать пользователя: {firstName} {lastName}, email: {email}")
    public WsRegistrationPage register(String firstName, String lastName, String email, String password) {
        selectMaleGendor()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(password)
                .submitRegistrationButton()
                .checkRegistrationCompleted();
        return this;
    }

    @Step("Проверить, что страница регистрации открыта")
    public WsRegistrationPage verifyRegistrationOpen() {
        pageTitle.shouldHave(text("Register"));
        return this;
    }

    @Step("Выбрать пол")
    public WsRegistrationPage selectMaleGendor() {
        maleGenderRadio.click();
        return this;
    }

    @Step("Ввести имя: {firstName}")
    public WsRegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести фамилию: {lastName}")
    public WsRegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести email: {email}")
    public WsRegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public WsRegistrationPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Подтвердить пароль")
    public WsRegistrationPage setConfirmPassword(String password) {
        passwordConfirmInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку 'Register'")
    public WsRegistrationPage submitRegistrationButton() {
        registrationInput.click();
        return this;
    }

    @Step("Проверить сообщение об успешной регистрации")
    public WsRegistrationPage checkRegistrationCompleted() {
        resultText.shouldHave(text("Your registration completed"));
        return this;
    }

    @Step("Проверить, что пользователь вошел под email: {email}")
    public WsRegistrationPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }
}
