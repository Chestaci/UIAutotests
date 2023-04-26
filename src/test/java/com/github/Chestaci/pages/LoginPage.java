package com.github.Chestaci.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект страницы авторизации
 */
public class LoginPage extends Page {

    /**
     * Определение локатора поля ввода Username
     */
    @FindBy(css = "input[id=username]")
    private WebElement usernameField;
    /**
     * Определение локатора поля ввода Password
     */
    @FindBy(css = "input[id=password]")
    private WebElement passwordField;

    /**
     * Определение локатора поля ввода Username description
     */
    @FindBy(css = "input[id*=input_username_0]")
    private WebElement usernameDescriptionField;

    /**
     * Определение локатора поля кнопки авторизации
     */
    @FindBy(css = "[ng-click*=login]")
    private WebElement loginButton;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для ввода
     *
     * @param username Имя
     */
    private void inputUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfAllElements(usernameField));
        usernameField.sendKeys(username);
    }

    /**
     * Метод для ввода
     *
     * @param password Пароль
     */
    private void inputPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfAllElements(passwordField));
        passwordField.sendKeys(password);
    }

    /**
     * Метод для ввода
     *
     * @param usernameDescription Username description
     */
    private void inputUsernameDescription(String usernameDescription) {
        wait.until(ExpectedConditions.visibilityOfAllElements(usernameDescriptionField));
        usernameDescriptionField.sendKeys(usernameDescription);
    }

    /**
     * Метод для осуществления нажатия на кнопку авторизации
     */
    private void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOfAllElements(loginButton));
        loginButton.click();
    }

    /**
     * Метод для осуществления действий по заполнению полей формы параметрами
     *
     * @param username            Имя
     * @param password            Пароль
     * @param usernameDescription Username description,
     *                            а также нажатия кнопки авторизации
     */
    public HomePage fillFieldsAndClick(String username, String password, String usernameDescription) {
        inputUsername(username);
        inputPassword(password);
        inputUsernameDescription(usernameDescription);
        clickLoginButton();
        return new HomePage(this.driver);
    }

}
