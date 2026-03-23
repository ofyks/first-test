package com.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexResultPage {
    private final SelenideElement closeWindow = $(".DistributionButtonClose_view_cross");
    private final SelenideElement openSite = $(byText("github.com"));

    public YandexResultPage closeDefaultWindow() {
        closeWindow.click();
        return this;
    }

    public YandexResultPage openSiteGitHub() {
        openSite.click();
        return this;
    }

    public <T> T switchToWindow(int index, Class<T> pageClass) {
        switchTo().window(index);
        return page(pageClass);
    }
}
