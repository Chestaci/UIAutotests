package com.github.Chestaci.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Объект страницы Practice Site 1
 */
public class PracticeSiteOnePage extends Page {


    /**
     * Определение локатора формы регистрации при переходе на Practice Site 1
     */
    @FindBy(xpath = "//*[contains(text(), 'Dummy Registration Form')]")
    public WebElement form;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public PracticeSiteOnePage(WebDriver driver) {
        super(driver);
    }
}
