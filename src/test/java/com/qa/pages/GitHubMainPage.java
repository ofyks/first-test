package com.qa.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GitHubMainPage {
    private final SelenideElement clickSearch = $(".header-search-button");
    private final SelenideElement searchText = $("#query-builder-test");
    private final SelenideElement clickButton = $(".ActionListItem-description");
    private final SelenideElement openProject = $(".hkFRpV");
    private final ElementsCollection checkName = $$(".AuthorDisplayName-module__Text__OP5Q9");

    public GitHubMainPage clickSearchMenu() {
        clickSearch.click();
        return this;
    }

    public GitHubMainPage searchTextMenu(String query) {
        searchText.setValue(query);
        return this;
    }

    public GitHubMainPage clickButtonSearch() {
        clickButton.click();
        return this;
    }

    public GitHubMainPage openProjectGitHub() {
        openProject.click();
        return this;
    }

    public GitHubMainPage checkNameOwner(String ownerName) {
        checkName.get(0).shouldHave(text(ownerName));
        return this;
    }
}
