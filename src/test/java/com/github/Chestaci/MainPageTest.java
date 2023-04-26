package com.github.Chestaci;

import com.github.Chestaci.pages.MainPage;
import com.github.Chestaci.pages.PracticeSiteOnePage;
import com.github.Chestaci.utils.ConfProperties;
import com.github.Chestaci.utils.WebDriverUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Тесты главной страницы
 */
public class MainPageTest {
    private MainPage mainPage;
    private WebDriver driver;

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    void setUp() {
        driver = WebDriverUtils.getPreparedDriver();
        mainPage = new MainPage(driver);
        driver.get(ConfProperties.getProperty("main_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест наличия элементов на странице: заголовок, футер, меню
     */
    @Test
    public void elementPresenceTest() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(mainPage.isHeaderPresent())
                    .withFailMessage("Заголовок не присутствует на странице").isTrue();
            softly.assertThat(mainPage.isFooterPresent())
                    .withFailMessage("Футер не присутствует на странице").isTrue();
            softly.assertThat(mainPage.isMenuPresent())
                    .withFailMessage("Меню не присутствует на странице").isTrue();
        });
    }

    /**
     * Тест слайдера
     */
    @Test(priority = -1)
    public void sliderTest() {
        mainPage.clickRightSliderButton();
        Assertions.assertThat(mainPage.isSlideFourPresent())
                .withFailMessage("Слайдер не работает").isTrue();
    }

    /**
     * Тест отображения основного меню в шапке при скроллинге на 1000 пикселей вниз
     */
    @Test
    public void scrollTest() {
        Assertions.assertThat(mainPage.isMenuPresent())
                .withFailMessage("Меню изначально не присутствует на странице").isTrue();
        mainPage.scroll(1000);
        Assertions.assertThat(mainPage.isMenuPresent())
                .withFailMessage("Меню при прокрутке не присутствует на странице").isTrue();
    }

    /**
     * Тест отображения основного меню в шапке при скроллинге до футера
     */
    @Test
    public void scrollToElementTest() {
        Assertions.assertThat(mainPage.isMenuPresent())
                .withFailMessage("Меню изначально не присутствует на странице").isTrue();
        mainPage.scrollToElement(mainPage.footer);
        Assertions.assertThat(mainPage.isMenuPresent())
                .withFailMessage("Меню при прокрутке не присутствует на странице").isTrue();
    }

    /**
     * Тест перехода по меню Resources -> Practice Site 1
     */
    @Test
    public void menuTransitionTest() {
        mainPage.menuResources.click();
        PracticeSiteOnePage practiceSiteOnePage = mainPage.clickPracticeSiteOneButton();
        Assertions.assertThat(practiceSiteOnePage.form.getText()).isEqualTo("DUMMY REGISTRATION FORM");
        driver.navigate().back();
    }
}
