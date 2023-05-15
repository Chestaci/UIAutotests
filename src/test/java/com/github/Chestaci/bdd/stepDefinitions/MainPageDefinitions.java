package com.github.Chestaci.bdd.stepDefinitions;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.MainPage;
import com.github.Chestaci.pages.way2automation.PracticeSiteOnePage;
import com.github.Chestaci.utils.ConfProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;

/**
 * Тесты главной страницы сайта www.way2automation.com
 */
@SuppressWarnings("NonAsciiCharacters")
public class MainPageDefinitions {

    private WebDriver driver;
    private MainPage mainPage;
    private PracticeSiteOnePage practiceSiteOnePage;

    @Before
    public void setUpTest() throws MalformedURLException {
        driver = DriverManager.getPreparedDriver("false", "chrome");
    }

    @Дано("пользователь заходит на главную страницу")
    public void пользователь_заходит_на_главную_страницу() {
        mainPage = new MainPage(driver);
        driver.get(ConfProperties.getProperty("main_page"));
    }

    @Тогда("пользователь видит, что элементы присутствуют на странице")
    public void пользователь_видит_что_элементы_присутствуют_на_странице() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(mainPage.isHeaderPresent())
                    .withFailMessage("Заголовок не присутствует на странице").isTrue();
            softly.assertThat(mainPage.isFooterPresent())
                    .withFailMessage("Футер не присутствует на странице").isTrue();
            softly.assertThat(mainPage.isMenuPresent())
                    .withFailMessage("Меню не присутствует на странице").isTrue();
        });
    }

    @Когда("пользователь нажимает на правую кнопку слайдера")
    public void пользователь_нажимает_на_правую_кнопку_слайдера() {
        mainPage.clickRightSliderButton();
    }

    @Тогда("слайдер показывает следующий слайд")
    public void слайдер_показывает_следующий_слайд() {
        Assertions.assertThat(mainPage.isSlideFourPresent())
                .withFailMessage("Слайдер не работает").isTrue();
    }

    @Когда("пользователь прокручивает вниз {int} пикселей")
    public void пользователь_прокручивает_вниз_пикселей(Integer pixel) {
        mainPage.scroll(pixel);
    }

    @Тогда("пользователь все ещё видит основное меню")
    public void пользователь_все_ещё_видит_основное_меню() {
        Assertions.assertThat(mainPage.isMenuPresent())
                .withFailMessage("Меню при прокрутке не присутствует на странице").isTrue();
    }

    @Когда("пользователь прокручивает до заданного элемента")
    public void пользователь_прокручивает_до_заданного_элемента() {
        mainPage.scrollToElement(mainPage.footer);
    }

    @Когда("пользователь нажимает на кнопку меню")
    public void пользователь_нажимает_на_кнопку_меню() {
        mainPage.clickMenuResources();
    }

    @Когда("в меню нажимает на кнопку перехода на другую страницу")
    public void в_меню_нажимает_на_кнопку_перехода_на_другую_страницу() {
        practiceSiteOnePage = mainPage.clickPracticeSiteOneButton();
    }

    @Тогда("пользователь видит, что перешел на эту страницу")
    public void пользователь_видит_что_перешел_на_эту_страницу() {
        Assertions.assertThat(practiceSiteOnePage.form.getText()).isEqualTo("DUMMY REGISTRATION FORM");
        driver.navigate().back();
    }

    @After(order = 1)
    public void saveScreenshotPNGCucumber(Scenario scenario) throws IllegalMonitorStateException {
        if (scenario.isFailed()) {
            Allure.addAttachment("Page screenshot", new ByteArrayInputStream(((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES)));
        }
    }

    @After(order = 0)
    public void tearDown() {
        driver.quit();
    }
}
