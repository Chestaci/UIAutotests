package com.github.Chestaci.pages.way2automation;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект страницы для работы с всплывающими оповещениями
 */
public class AlertPage extends Page {

    /**
     * Определение локатора кнопки Input Alert
     */
    @FindBy(xpath = "//a[text()='Input Alert']")
    private WebElement inputAlertButton;

    /**
     * Определение локатора рамки
     */
    @FindBy(xpath = "//iframe[@src='alert/input-alert.html']")
    private WebElement iframe;

    /**
     * Определение локатора кнопки демонстрации оповещения
     */
    @FindBy(css = "body>button")
    private WebElement alertDemonstrationButton;

    /**
     * Определение локатора ответного сообщения
     */
    @FindBy(css = "#demo")
    private WebElement text;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public AlertPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для осуществления нажатия на кнопку Input Alert
     */
    @Step("Нажатие на кнопку Input Alert")
    public void clickInputAlertButton() {
        wait.until(ExpectedConditions.visibilityOf(inputAlertButton));
        inputAlertButton.click();
    }

    /**
     * Метод для осуществления нажатия на кнопку демонстрации оповещения
     */
    @Step("Нажатие на кнопку демонстрации оповещения")
    public void clickAlertDemonstrationButton() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        alertDemonstrationButton.click();
    }

    /**
     * Метод получения ответного сообщения
     *
     * @return ответное сообщение
     */
    @Step("Получение ответного сообщения")
    public String getText() {
        return text.getText();
    }
}
