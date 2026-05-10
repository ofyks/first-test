package ru.bulgakov.webshop.steps;

import net.datafaker.Faker;
import ru.bulgakov.webshop.page.WsRegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION;

public class AuthSteps {
    private static final Faker faker = new Faker();
    public void registerNewUser() {
        open(WEB_SHOP_REGISTRATION, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.harryPotter().character() + faker.number().positive());
    }
}
