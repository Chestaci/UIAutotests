package com.github.Chestaci.bdd.stepDefinitions;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.HomePage;
import com.github.Chestaci.pages.way2automation.LoginPage;
import com.github.Chestaci.utils.ConfProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;

/**
 * Тесты авторизации сайта www.way2automation.com
 */
@SuppressWarnings("NonAsciiCharacters")
public class AuthorizationDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @Before
    public void setUpTest() throws MalformedURLException {
        driver = DriverManager.getPreparedDriver("false", "chrome");
    }

    @Дано("пользователь заходит на страницу авторизации")
    public void пользователь_заходит_на_страницу_авторизации() {
        loginPage = new LoginPage(driver);
        driver.get(ConfProperties.getProperty("login_page"));
    }

    @Когда("пользователь вводит правильные данные: {string}, {string}, {string}")
    public void пользователь_вводит_правильные_данные(String username, String password, String usernameDescription) {
        homePage = loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
    }

    @Тогда("пользователь авторизируется на сайте")
    public void пользователь_авторизируется_на_сайте() {
        Assertions.assertThat(homePage.getWelcomeMessage()).isEqualTo("You're logged in!!");
        driver.navigate().back();
    }

    @Когда("пользователь вводит неправильные данные: {string}, {string}, {string}")
    public void пользователь_вводит_неправильные_данные(String username, String password, String usernameDescription) {
        loginPage.fillFieldsAndClick(
                username,
                password,
                usernameDescription);
    }

    @Тогда("появляется предупреждение о введении неправильных данных")
    public void появляется_предупреждение_о_введении_неправильных_данных() {
        Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Username or password is incorrect");
        loginPage.clearFields();
    }

    @Когда("пользователь неправильно вводит данные: {string}, {string}, {string}")
    public void пользователь_неправильно_вводит_данные(String username, String password, String usernameDescription) {
        loginPage.fillFields(
                username,
                password,
                usernameDescription);
    }

    @Тогда("пользователь не может нажать на кнопку авторизации, так как она неактивна")
    public void пользователь_не_может_нажать_на_кнопку_авторизации_так_как_она_неактивна() {
        Assertions.assertThat(loginPage.clickLoginButton()).isFalse();
        loginPage.clearFields();
    }

    @After(order = 1)
    public void saveScreenshotPNGCucumber(Scenario scenario) throws IllegalMonitorStateException {
        if (scenario.isFailed()) {
            Allure.addAttachment("Page screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @After(order = 0)
    public void tearDown() {
        driver.quit();
    }
}
