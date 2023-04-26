package com.github.Chestaci.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Объект домашней страницы пользователя
 */
public class HomePage extends Page {

    /**
     * Определение локатора приветствия
     */
    @FindBy(xpath = "//*[contains(text(), 'logged in')]")
    public WebElement text;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
}
