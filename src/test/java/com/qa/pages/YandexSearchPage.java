package com.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class YandexSearchPage {

    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $(".search3__button");

    public YandexSearchPage search(String query) {
        searchInput.setValue(query);
        return this;
    }

    public YandexResultPage clickSearch() {
        submitButton.click();
        return page(YandexResultPage.class);
    }
}

