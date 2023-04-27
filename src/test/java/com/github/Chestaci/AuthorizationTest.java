package com.github.Chestaci;

import com.github.Chestaci.pages.HomePage;
import com.github.Chestaci.pages.LoginPage;
import com.github.Chestaci.utils.ConfProperties;
import com.github.Chestaci.utils.WebDriverUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Тесты авторизации
 */
@Epic("Тесты авторизации")
public class AuthorizationTest {
    private LoginPage loginPage;
    private WebDriver driver;

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    void setUp() {
        driver = WebDriverUtils.getPreparedDriver();
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
    public void successfulAuthorizationTest(String username, String password, String usernameDescription) throws InterruptedException {
        HomePage homePage = loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
        Thread.sleep(10000);
        loginPage.clickLoginButton();
        Thread.sleep(10000);
        Assertions.assertThat(homePage.getWelcomeMessage()).isEqualTo("You're logged in!!");
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
    public void notSuccessfulAuthorizationTest(String username, String password, String usernameDescription) {
        loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
        loginPage.clickLoginButton();
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
        loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
        Assertions.assertThat(loginPage.clickLoginButton()).isFalse();
        loginPage.clearFields();
    }
}
