package com.github.Chestaci.sql_test;

import com.github.Chestaci.pages.sql.SQLHomePage;
import com.github.Chestaci.pages.sql.SQLMainPage;
import com.github.Chestaci.utils.ConfProperties;
import com.github.Chestaci.driver_manager.DriverManager;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Тесты авторизации сайта www.sql-ex.ru
 */
@Epic("Тесты авторизации сайта www.sql-ex.ru")
public class SQLAuthorizationTest {
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
    void setUpTest(@Optional("false") String remote, @Optional ("chrome")String browserName) throws MalformedURLException {
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
     * Данные для теста успешной авторизации
     *
     * @return набор данных для успешной авторизации
     */
    @DataProvider(name = "authorizationData")
    public Object[][] getAuthorizationData() {
        return new Object[][]{{"chestaci", "123456789"},};
    }

    /**
     * Тест успешной авторизации, использующий для первого входа логин и пароль,
     * а для следующего входа использует сохранённые cookies.
     *
     * @param login    Логин
     * @param password Пароль
     */
    @Test(dataProvider = "authorizationData")
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест авторизации пользователя с сохранёнными cookies")
    @Story("Тест авторизации пользователя, использующий для первого входа логин и пароль,"
            + "а для следующего входа - сохранённые cookies.")
    public void cookieAuthenticationTest(String login, String password) {
        sqlMainPage.authWithLoginAndPassword(login, password);
        driver.manage().deleteAllCookies();
        driver.navigate().back();
        SQLHomePage sqlHomePage = sqlMainPage.authWithCookies();
        Assertions.assertThat(sqlHomePage.getNickname()).isEqualTo("chesta");
    }
}

