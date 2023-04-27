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
     * Тест успешной авторизации
     */
    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест авторизации пользователя")
    @Story("Тест успешной авторизации пользователя")
    public void successfulAuthorizationTest() {
        HomePage homePage = loginPage.fillFieldsAndClick(
                ConfProperties.getProperty("username"),
                ConfProperties.getProperty("password"),
                ConfProperties.getProperty("username"));
        Assertions.assertThat(homePage.text.getText()).isEqualTo("You're logged in!!");
    }
}
