package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {

    private final SelenideElement productName = $("[itemprop=name]");
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final ElementsCollection processorOptions = $$("dl dd ul li");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");

    @Step("Получить название товара")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Получить цену товара")
    public String getProductPrice() {
        return productPrice.getText();
    }

    @Step("Выбрать процессор с индексом: {index}")
    public ProductPage selectProcessor(int index) {
        processorOptions.get(index).$("li input").click();
        return this;
    }

    @Step("Установить количество товара: {quantity}")
    public ProductPage setQuantity(String quantity) {
        quantityInput.setValue(quantity);
        return this;
    }

    @Step("Добавить товар в корзину")
    public CartPage addToCart() {
        addToCartButton.click();
        return new CartPage();
    }
}