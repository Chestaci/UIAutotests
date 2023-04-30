package com.github.Chestaci.pages.sql;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект домашней страницы пользователя SQL
 */
public class SQLHomePage extends Page {

    /**
     * Определение локатора приветствия
     */
    @FindBy(xpath = "//a[text()='chesta']")
    private WebElement nickname;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public SQLHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод получения никнейма
     *
     * @return никнейм
     */
    @Step("Получение никнейма авторизированного пользователя")
    public String getNickname() {
        wait.until(ExpectedConditions.visibilityOf(nickname));
        return nickname.getText();
    }
}
