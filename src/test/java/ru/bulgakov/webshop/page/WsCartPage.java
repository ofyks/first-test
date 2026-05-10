package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class WsCartPage {

    private final ElementsCollection topMenuItems = $$("ul.top-menu li a");
    private final SelenideElement desktopsLink = $(byText("Desktops"));

    private final SelenideElement productName = $("[itemprop=name]");
    private final SelenideElement productPrice = $("[itemprop=price]");
    private final ElementsCollection processorOptions = $$("dl dd ul li");
    private final SelenideElement quantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement successNotification = $("div.bar-notification.success");
    private final SelenideElement cartQuantityBadge = $("span.cart-qty");
    private final SelenideElement cartLink = $("a.ico-cart");

    private final SelenideElement cartItemName = $("a.product-name");
    private final SelenideElement cartQuantityInput = $("input.qty-input");
    private final SelenideElement productUnitPrice = $("span.product-unit-price");
    private final SelenideElement productSubtotal = $("span.product-subtotal");


    public WsCartPage navigateToDesktops() {
        topMenuItems.get(1).hover();
        desktopsLink.click();
        return this;
    }


    public WsCartPage selectFirstProduct() {
        $$("div.product-grid div").get(0).click();
        return this;
    }


    public String getItemName() {
        return productName.getText();
    }

    public String getItemPriceFromProductPage() {
        return productPrice.getText();
    }


    public WsCartPage selectProcessor(int index) {
        processorOptions.get(index).$("li input").click();
        return this;
    }


    public WsCartPage setQuantity(String quantity) {
        quantityInput.setValue(quantity);
        return this;
    }


    public WsCartPage addToCart() {
        addToCartButton.click();
        return this;
    }

    public WsCartPage verifySuccessNotification() {
        successNotification.shouldBe(visible);
        return this;
    }

    public WsCartPage verifyCartQuantityBadge(String expectedQuantity) {
        cartQuantityBadge.shouldHave(text("(" + expectedQuantity + ")"));
        return this;
    }


    public WsCartPage openCart() {
        cartLink.click();
        return this;
    }


    public WsCartPage verifyItemNameInCart(String expectedName) {
        cartItemName.shouldHave(text(expectedName));
        return this;
    }


    public String getQuantityInCart() {
        return cartQuantityInput.getAttribute("value");
    }


    public WsCartPage verifyQuantityInCart(String expectedQuantity) {
        assertEquals(expectedQuantity, getQuantityInCart());
        return this;
    }


    public String getUnitPriceFromCart() {
        return productUnitPrice.getText();
    }


    public WsCartPage verifySubtotalCalculated(String price, String quantity) {
        float expectedTotal = Float.parseFloat(price) * Float.parseFloat(quantity);
        productSubtotal.shouldHave(text(String.valueOf(expectedTotal)));
        return this;
    }
}