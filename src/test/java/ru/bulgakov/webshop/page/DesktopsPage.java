package ru.bulgakov.webshop.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage {

    @Step("Выбрать первый товар из списка")
    public ProductPage selectFirstProduct() {
        SelenideElement firstProduct = $$("div.product-grid div").get(0);
        firstProduct.click();
        return new ProductPage();
    }
}