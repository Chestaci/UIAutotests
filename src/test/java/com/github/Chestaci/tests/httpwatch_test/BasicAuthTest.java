package com.github.Chestaci.tests.httpwatch_test;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.httpwatch.BasicAuthPage;
import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
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
 * Тесты работы с Basic Authorization сайта www.httpwatch.com
 */
@Epic("Тесты работы с авторизацией во всплывающем окне сайта www.httpwatch.com")
public class BasicAuthTest {
    private WebDriver driver;
    private BasicAuthPage basicAuthPage;

    @BeforeMethod
    public void setUpMethod(final ITestContext context) {
        context.setAttribute("driver", driver);
    }

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    @Parameters({"remote", "browserName"})
    void setUpTest(@Optional("false") String remote, @Optional("chrome") String browserName) throws MalformedURLException {
        driver = DriverManager.getPreparedDriver(remote, browserName);
        basicAuthPage = new BasicAuthPage(driver);
        driver.get(ConfProperties.getProperty("basic_auth_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест работы с Basic Authorization
     */
    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест работы с Basic Authorization")
    @Story("Тест авторизации Basic Authorization")
    public void basicAuthTest() {
        basicAuthPage.clickDisplayImageButton();
        Assertions.assertThat(basicAuthPage.getAttribute()).contains(ConfProperties.getProperty("basic_auth_login"));
    }
}
