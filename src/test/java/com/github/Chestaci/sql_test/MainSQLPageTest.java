package com.github.Chestaci.sql_test;

import com.github.Chestaci.pages.sql.SQLMainPage;
import com.github.Chestaci.utils.ConfProperties;
import com.github.Chestaci.driver_manager.DriverManager;
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
 * Тесты главной страницы сайта www.sql-ex.ru
 */
@Epic("Тесты главной страницы сайта www.sql-ex.ru")
public class MainSQLPageTest {
    private WebDriver driver;
    private SQLMainPage sqlMainPage;

    @BeforeMethod
    public void setUpMethod(final ITestContext context) {
        context.setAttribute("driver", driver);
    }

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    @Parameters({"remote", "browserName"})
    void setUpTest(@Optional("true") String remote, @Optional("ie") String browserName) throws MalformedURLException {
        driver = DriverManager.getPreparedDriver(remote, browserName);
        sqlMainPage = new SQLMainPage(driver);
        driver.get(ConfProperties.getProperty("sql_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест перевода фокуса из поля ввода
     */
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест фокуса")
    @Story("Тест перевода фокуса из поля ввода логина")
    public void blurTest(){
        sqlMainPage.blur(sqlMainPage.loginField);
        Assertions.assertThat(driver.switchTo().activeElement().getAttribute("name")).isNotEqualTo("login");
    }

    /**
     * Тест наличия скролла на странице
     */
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест скролла")
    @Story("Тест наличия горизонтального и вертикального скорлла на странице")
    public void checkScrollStatus(){
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(sqlMainPage.checkHorizontalScrollStatus()).isFalse();
            softly.assertThat(sqlMainPage.checkVerticalScrollStatus()).isTrue();
        });
    }
}
