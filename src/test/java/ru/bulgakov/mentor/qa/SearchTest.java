package ru.bulgakov.mentor.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.mentor.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.*;

public class SearchTest {

    @Test
    @DisplayName("Проверить, что цена обучения 47 000 рублей")
    void mentoringPrice47000Test() {
        open("https://ya.ru/", YandexSearchPage.class)
                .search("ivan qa")
                .submit()
                .closeDefaultBrowserWindow()
                .openLink("ivan.ru")
                .clickToPrice()
                .clickTryQaMentor()
                .clickButtonTextRunPay()
                .checkPrice();
    }
        }







// создаем класс под страницу -
// выписываем меиоды для взаимодействия -
// вытаскиваем из теста дейстивя -
// выносим локаторы в переменные -






//        sleep(3000);
//        switchTo().window(1);
//        $$(".t-menu__list li").last().click();
//        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
//        $(byText("Бегу оплачивать")).click();
//
//        switchTo().window(2);
//        $(".styles-module-scss-module__t92_WG__price\n h2").shouldHave(text("₽ 47 000 "));
//
//    }
//}

//        Configuration.holdBrowserOpen = true; // браузер не закрывается после проверки
//        Configuration.pageLoadTimeout = 100000; // таймаут прогрузги страницы
//        Configuration.timeout = 100000; // таймаут прогрузги элементов

/*
 * Тест-кейс - проверить, что предоплата по обучению - 47000 рублей
 * 1. открыть поисковие
 * 2. ввести данные сайта
 * 3. нажать кнопку поиск
 * 4. в поисковой выдаче найти нужный сайт, кликнуть на него
 * 5. нажать на кнопку стоимость
 * 6. нажать на кнопку "Хочу  в AQA"
 * 7. нажать на кнопку "Бегу оплачивать"
 * 8. проверить, что к оплате 47 000 рублей
 * */