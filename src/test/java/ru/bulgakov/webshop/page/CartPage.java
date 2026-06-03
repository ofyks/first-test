package ru.bulgakov.webshop.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQuantityBadge = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");

    private final SelenideElement cartItemName = $("a.product-name");
    private final SelenideElement cartQuantityInput = $("input.qty-input");
    private final SelenideElement productUnitPrice = $("span.product-unit-price");
    private final SelenideElement productSubtotal = $("span.product-subtotal");

    @Step("Проверить отображение уведомления об успехе")
    public CartPage verifySuccessNotification() {
        successNotification.shouldBe(visible);
        return this;
    }

    @Step("Проверить бейдж количества товаров в корзине: {expectedQuantity}")
    public CartPage verifyCartQuantityBadge(String expectedQuantity) {
        cartQuantityBadge.shouldHave(com.codeborne.selenide.Condition.text("(" + expectedQuantity + ")"));
        return this;
    }

    @Step("Открыть корзину")
    public CartPage openCart() {
        cartLink.click();
        return this;
    }

    @Step("Получить название товара в корзине")
    public String getItemName() {
        return cartItemName.getText();
    }

    @Step("Получить цену за единицу товара")
    public String getQuantity() {
        return cartQuantityInput.getAttribute("value");
    }

    @Step("Проверить содержимое корзины: товар '{itemName}', кол-во '{quantity}', сумма '{expectedTotal}'")
    public CartPage verifyCartContents(String itemName, String quantity, String expectedTotal) {
        cartItemName.shouldHave(text(itemName));
        cartQuantityInput.shouldHave(value(quantity));
        productSubtotal.shouldHave(text(expectedTotal));
        return this;
    }

    public String getSubtotal() {
        return productSubtotal.getText();
    }

    public String getUnitPrice() {
        return productUnitPrice.getText();
    }
}