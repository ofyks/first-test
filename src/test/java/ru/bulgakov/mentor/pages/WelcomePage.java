package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {
    private final SelenideElement goToPrice = $$(".t-menu__list li").last();
    private final SelenideElement tryQaMentorButton =$x("/html/body/div[1]/div[42]/div/div/div[32]/div/a");
    private final SelenideElement clickRunPay =$(byText("Бегу оплачивать"));


    public WelcomePage clickToPrice() {
        switchTo().window(1);
        goToPrice.click();

        return this;
    }
    public WelcomePage clickTryQaMentor() {
        tryQaMentorButton.click();

        return this;
    }
    public WelcomePage clickButtonTextRunPay() {
        switchTo().window(1);
        clickRunPay.click();
        sleep(5000);

        return this;
    }
    public PaymentPage checkPrice(){
        switchTo().window(2);
        $(".styles-module-scss-module__t92_WG__price\n h2").shouldHave(text("₽ 47 000 "));

        return new PaymentPage();
    }
}
