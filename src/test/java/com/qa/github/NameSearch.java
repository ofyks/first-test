package com.qa.github;

import com.qa.pages.GitHubMainPage;
import com.qa.pages.YandexSearchPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class NameSearch {

    @Test
    @DisplayName("Проверка владельца репозитория (цепочка с разрывом на switchTo)")
    void checkRepositoriesWithSwitchBreak() {
        open("https://ya.ru/", YandexSearchPage.class)
                .search("Git Hub")
                .clickSearch()
                .closeDefaultWindow()
                .openSiteGitHub();

        switchTo().window(1);

        page(GitHubMainPage.class)
                .clickSearchMenu()
                .searchTextMenu("java-automation-qa/getting-started")
                .clickButtonSearch()
                .openProjectGitHub()
                .checkNameOwner("Владислав Юстус");
    }

    @Test
    @DisplayName("Проверка владельца репозитория (дженерик switchToWindow в цепочке)")
    void checkRepositoriesWithGenericSwitch() {
        open("https://ya.ru/", YandexSearchPage.class)
                .search("Git Hub")
                .clickSearch()
                .closeDefaultWindow()
                .openSiteGitHub()
                .switchToWindow(1, GitHubMainPage.class)
                .clickSearchMenu()
                .searchTextMenu("java-automation-qa/getting-started")
                .clickButtonSearch()
                .openProjectGitHub()
                .checkNameOwner("Владислав Юстус");
    }
}

//тест