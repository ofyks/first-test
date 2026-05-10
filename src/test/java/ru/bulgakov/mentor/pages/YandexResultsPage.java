package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YandexResultsPage {
private final SelenideElement closeWindow = $(".DistributionButtonClose");

  public YandexResultsPage closeDefaultBrowserWindow(){
      closeWindow.click();

    return this;
  }
  public WelcomePage openLink(String websiteName){
      $(byText(websiteName)).click();

    return new WelcomePage();
  }
}










