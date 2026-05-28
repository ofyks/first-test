package ru.bulgakov.webshop.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private final ElementsCollection topMenuItems = $$("ul.top-menu li a");
    private final SelenideElement desktopsLink = $(byText("Desktops"));

    @Step("Навести курсор на меню 'Computers'")
    public MainPage hoverComputersMenu() {
        topMenuItems.get(1).hover();
        return this;
    }

    @Step("Перейти в раздел 'Desktops'")
    public DesktopsPage selectDesktops() {
        desktopsLink.click();
        return new DesktopsPage();
    }
}