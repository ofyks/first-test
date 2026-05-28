package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.page.CartPage;
import ru.bulgakov.webshop.page.DesktopsPage;
import ru.bulgakov.webshop.page.MainPage;
import ru.bulgakov.webshop.page.ProductPage;
import ru.bulgakov.webshop.steps.AuthSteps;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;


@Epic("Покупки")
@Feature("Корзина товаров")
public class CartTest extends TestBase {

    private final AuthSteps authSteps = new AuthSteps();

    @Test
    @DisplayName("Добавление товара в корзину с выбором процессора и проверкой итоговой суммы")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Кирюха")
    @Link(name = "CART-1", url = "https://demowebshop.tricentis.com/computers")
    void addItemToCartTest() {
        authSteps.registerNewUser();

        ProductPage productPage = open(WEB_SHOP_URL, MainPage.class)
                .hoverComputersMenu()
                .selectDesktops()
                .selectFirstProduct();

        String itemName = productPage.getProductName();
        String itemPrice = productPage.getProductPrice();
        String itemQuantity = "4";
        int processorIndex = 11;

        productPage.selectProcessor(processorIndex)
                .setQuantity(itemQuantity)
                .addToCart()
                .verifySuccessNotification()
                .verifyCartQuantityBadge(itemQuantity)
                .openCart()
                .verifyCartContents(
                        itemName,
                        itemQuantity,
                        calculateExpectedTotal(itemPrice, processorIndex, itemQuantity)
                );
    }

    private float getProcessorSurcharge(int processorIndex) {
        return switch (processorIndex) {
            case 0 -> 0f;
            case 1 -> 15f;
            case 2 -> 100f;
            default -> throw new IllegalArgumentException("Unknown processor index: " + processorIndex);
        };
    }

    private String calculateExpectedTotal(String price, int processorIndex, String quantity) {
        float basePrice = Float.parseFloat(price.replace("$", "").replace(",", "."));
        float surcharge = getProcessorSurcharge(processorIndex);
        float total = (basePrice + surcharge) * Float.parseFloat(quantity);
        return String.format(Locale.US, "%.2f", total);
    }
}