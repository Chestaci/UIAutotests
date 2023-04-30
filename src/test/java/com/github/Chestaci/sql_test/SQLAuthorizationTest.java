package com.github.Chestaci.sql_test;

import com.github.Chestaci.pages.sql.SQLHomePage;
import com.github.Chestaci.pages.sql.SQLMainPage;
import com.github.Chestaci.utils.ConfProperties;
import com.github.Chestaci.utils.WebDriverUtils;
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
import org.testng.annotations.Test;

/**
 * Тесты авторизации
 */
@Epic("Тесты авторизации")
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
    void setUpTest() {
        driver = WebDriverUtils.getPreparedDriver();
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
     * Тест успешной авторизации, использующий для входа логин и пароль, если cookies не сохранены,
     * иначе для входа использует сохранённые cookies.
     *
     * @param login    Логин
     * @param password Пароль
     */
    @Test(dataProvider = "authorizationData")
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест авторизации пользователя")
    @Story("Тест успешной авторизации пользователя, использующий для первого входа логин и пароль,"
            + "а для следующего входа - сохранённые cookies.")
    public void successfulAuthorizationTest(String login, String password) {
        SQLHomePage sqlHomePage = sqlMainPage.authorization(login, password);
        Assertions.assertThat(sqlHomePage.getNickname()).isEqualTo("chesta");
    }
}

