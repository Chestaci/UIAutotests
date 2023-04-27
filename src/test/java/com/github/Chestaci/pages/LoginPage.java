package com.github.Chestaci.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
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
     * Определение локатора текста об ошибке авторизации
     */
    @FindBy (css = "[class*=alert-danger]")
    private WebElement error;

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
     *
     * @return true, если кнопка активна, иначе - false
     */
    @Step("Нажатие на кнопку авторизации")
    public boolean clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        if(loginButton.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
            return true;
        }return false;
    }

    /**
     * Метод для осуществления действий по заполнению полей формы параметрами
     *
     * @param username            Имя
     * @param password            Пароль
     * @param usernameDescription Username description
     */
    @Step("Заполнение полей ввода username: " +
            "{username}," + " password: {password}, usernameDescription: {usernameDescription} ")
    public HomePage fillFieldsAndClick(String username, String password, String usernameDescription) {
        inputUsername(username);
        inputPassword(password);
        inputUsernameDescription(usernameDescription);
        return new HomePage(this.driver);
    }

    /**
     * Метод для очистки полей ввода
     */
    @Step("Очистка полей ввода")
    public void clearFields(){
        usernameField.clear();
        passwordField.clear();
        usernameDescriptionField.clear();
    }

    /**
     * Метод получения сообщения об ошибке
     *
     * @return сообещние об ошибке
     */
    @Step("Получение сообщения об ошибке")
    public String getErrorMessage(){
        wait.until(ExpectedConditions.visibilityOf(error));
        return error.getText();
    }
}
