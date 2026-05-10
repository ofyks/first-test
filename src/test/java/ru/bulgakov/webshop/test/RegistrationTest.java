package ru.bulgakov.webshop.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.page.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
    private static final Faker faker = new Faker();

    @Test
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

//        $("a.ico-register").click();
//        $("div.page-title").shouldHave(text("Register"));
//        $("input#gender-male").click();
//        $("input#FirstName").setValue(faker.name().firstName());
//        $("input#LastName").setValue(faker.name().lastName());
//        $("input#Email").setValue(email);
//        $("input#Password").setValue(password);
//        $("input#ConfirmPassword").setValue(password);
//        $("input#register-button").click();
//
//        $("div.result").shouldHave(text("Your registration completed"));
//        $$("div.header-links ul li a").get(0).shouldHave(text(email));