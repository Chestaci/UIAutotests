package com.github.Chestaci.way2automation_test;

import com.github.Chestaci.pages.way2automation.HomePage;
import com.github.Chestaci.pages.way2automation.LoginPage;
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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Тесты авторизации сайта www.way2automation.com
 */
@Epic("Тесты авторизации сайта www.way2automation.com")
public class AuthorizationTest{
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpMethod(final ITestContext context) {
        context.setAttribute("driver", driver);
    }

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    @Parameters({"remote", "browserName"})
    void setUpTest(String remote, String browserName) throws MalformedURLException {
        driver = DriverManager.getPreparedDriver(remote, browserName);
        loginPage = new LoginPage(driver);
        driver.get(ConfProperties.getProperty("login_page"));
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
        return new Object[][]{
                {"angular", "password", "12dfRAsE3!@#$%^&*"},
        };
    }

    /**
     * Данные для теста неуспешной авторизации пользователя с неправильными данными
     *
     * @return набор данных для неуспешной авторизации
     */
    @DataProvider(name = "authorizationErrorData")
    public Object[][] getAuthorizationErrorData() {
        return new Object[][]{
                {"ang", "pass", "angular"},
                {"ANguLaR", "password", "angular"},
                {"angular", "PassWord", "angular"},
        };
    }

    /**
     * Данные для теста неправильного заполнения полей ввода
     *
     * @return набор данных для неправильного заполнения полей ввода
     */
    @DataProvider(name = "incorrectInputFields")
    public Object[][] getIncorrectInputFieldsData() {
        return new Object[][]{
                {"an", "password", "angular"},
                {"angular", "pa", "angular"},
                {"angular", "password", "an"},
                {"", "", ""}
        };
    }

    /**
     * Тест успешной авторизации
     *
     * @param username            Имя
     * @param password            Пароль
     * @param usernameDescription Username description
     */
    @Test(dataProvider = "authorizationData")
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест авторизации пользователя")
    @Story("Тест успешной авторизации пользователя")
    public void successfulAuthorizationTest(String username, String password, String usernameDescription) {
        HomePage homePage = loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
        Assertions.assertThat(homePage.getWelcomeMessage()).isEqualTo("You're logged in!!");
        driver.navigate().back();
    }

    /**
     * Тест неуспешной авторизации пользователя с неправильными данными
     *
     * @param username            Имя
     * @param password            Пароль
     * @param usernameDescription Username description
     */
    @Test(dataProvider = "authorizationErrorData")
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест авторизации пользователя")
    @Story("Тест неуспешной авторизации пользователя с неправильными данными")
    public void unSuccessfulAuthorizationTest(String username, String password, String usernameDescription) {
        loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Username or password is incorrect");
        loginPage.clearFields();
    }

    /**
     * Тест неправильного заполнения полей ввода
     *
     * @param username            Имя
     * @param password            Пароль
     * @param usernameDescription Username description
     */
    @Test(dataProvider = "incorrectInputFields")
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест авторизации пользователя")
    @Story("Тест неправильного заполнения полей ввода")
    public void incorrectInputFieldsTest(String username, String password, String usernameDescription) {
        loginPage.fillFields(
                username,
                password,
                usernameDescription);
        Assertions.assertThat(loginPage.clickLoginButton()).isFalse();
        loginPage.clearFields();
    }

    /**
     * Падающий тест для демонстрации прикрепления скриншотов в отчёте при падении теста
     */
    @Test
    public void failTestAuthorizationTest(){
        loginPage.clearFields();
        Assertions.fail("Fail authorization test");
    }
}
