package java.com.github.Chestaci.tests.way2automation_test;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.MainPage;
import com.github.Chestaci.pages.way2automation.PracticeSiteOnePage;
import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Тесты главной страницы сайта www.way2automation.com
 */
@Epic("Тесты главной страницы сайта www.way2automation.com")
public class MainPageTest{
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeMethod
    public void setUpMethod(final ITestContext context) {
        context.setAttribute("driver", driver);
    }

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    @Parameters({"remote", "browserName"})
    void setUpTest(@Optional("false") String remote, @Optional ("chrome")String browserName) throws MalformedURLException {
        driver = DriverManager.getPreparedDriver(remote, browserName);
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
    @Severity(value = SeverityLevel.CRITICAL)
    @Feature("Тест наличия элементов на странице")
    @Story("Тест наличия элементов на странице: заголовок, футер, меню")
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
     * Тест работы слайдера
     */
    @Test(priority = -2)
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест слайдера")
    @Story("Тест работы слайдера при нажатии на правую кнопку")
    public void sliderTest() {
        mainPage.clickRightSliderButton();
        Assertions.assertThat(mainPage.isSlideFourPresent())
                .withFailMessage("Слайдер не работает").isTrue();
    }

    /**
     * Тест отображения основного меню в шапке при скроллинге на 1000 пикселей вниз
     */
    @Test(priority = -4)
    @Severity(value = SeverityLevel.CRITICAL)
    @Feature("Тест отображения основного меню в шапке при скроллинге")
    @Story("Тест отображения основного меню в шапке при скроллинге на заданное количество пикселей вниз")
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
    @Test(priority = -3)
    @Severity(value = SeverityLevel.CRITICAL)
    @Feature("Тест отображения основного меню в шапке при скроллинге")
    @Story("Тест отображения основного меню в шапке при скроллинге до заданного элемента")
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
    @Test(priority = -1)
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест перехода по меню")
    @Story("Тест перехода по меню Resources -> Practice Site 1")
    public void menuTransitionTest() {
        mainPage.clickMenuResources();
        PracticeSiteOnePage practiceSiteOnePage = mainPage.clickPracticeSiteOneButton();
        Assertions.assertThat(practiceSiteOnePage.form.getText()).isEqualTo("DUMMY REGISTRATION FORM");
        driver.navigate().back();
    }

    /**
     * Падающий тест для демонстрации прикрепления скриншотов в отчёте при падении теста
     */
//    @Test(priority = -5)
//    @Severity(value = SeverityLevel.TRIVIAL)
//    @Feature("Падающий тест")
//    @Story("Падающий тест для демонстрации прикрепления скриншотов в отчёте")
//    public void failTestMainPage(){
//        mainPage.scrollToElement(mainPage.footer);
//        Assertions.fail("Fail main page test");
//    }
}
