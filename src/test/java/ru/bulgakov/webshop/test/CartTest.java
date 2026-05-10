package ru.bulgakov.webshop.test;

import com.codeborne.selenide.selector.ByText;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.page.WsCartPage;
import ru.bulgakov.webshop.page.WsRegistrationPage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {
    private static final Faker faker = new Faker();
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        open(WEB_SHOP_URL);

        WsCartPage cartPage = new WsCartPage();

        String itemQuantity = "4";

        cartPage.navigateToDesktops()
                .selectFirstProduct();

        String itemName = cartPage.getItemName();

        cartPage.selectProcessor(2)
                .setQuantity(itemQuantity)
                .addToCart()
                .verifySuccessNotification()
                .verifyCartQuantityBadge(itemQuantity)
                .openCart()
                .verifyItemNameInCart(itemName);

        String itemQuantityInCart = cartPage.getQuantityInCart();
        assertEquals(itemQuantity, itemQuantityInCart);

        cartPage.verifySubtotalCalculated(cartPage.getUnitPriceFromCart(), itemQuantity);

        System.out.println(1);
    }
}





//open(WEB_SHOP_URL);
//        $$("ul.top-menu li a").get(1).hover();
//        $(byText("Desktops")).click();
//        $$("div.product-grid div").get(0).click();
//
//        String itemName = $("[itemprop=name]").getText(); // itemName: "Build your own cheap computer"
//        String itemPrice = $("[itemprop=price]").getText(); // itemPrice: "800.00"
//        String itemQuantity = "2";
//
//
//        $$("dl dd ul li").get(0).$("li input").click();
//        $("input.qty-input").setValue(itemQuantity);
//        $("input.add-to-cart-button").click();
//        $("div.bar-notification.success").shouldBe(visible);
//        $("span.cart-qty").shouldHave(text("(" + itemQuantity + ")"));
//        $("a.ico-cart").click();
//
//        $("a.product-name").shouldHave(text(itemName));
//
//
//        String itemQuantityInCart = $("input.qty-input").getAttribute("value");
//        assertEquals(itemQuantity, itemQuantityInCart);
//
//
//        $("span.product-subtotal").shouldHave(text(String.valueOf(
//        Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));
//        }