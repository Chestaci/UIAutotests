package com.github.Chestaci.pages.httpwatch;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Объект страницы для работы с Basic Authorization
 */
public class BasicAuthPage extends Page {

    /**
     * Определение локатора подтверждения авторизации
     */
    @FindBy(css = "#downloadImg")
    private WebElement downloadImg;

    /**
     * Определение локатора кнопки Display Image
     */
    @FindBy(css = "#displayImage")
    private WebElement displayImageButton;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public BasicAuthPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для осуществления нажатия на кнопку Display Image
     */
    @Step("Нажатие на кнопку Display Image")
    public void clickDisplayImageButton() {
        displayImageButton.click();
    }

    /**
     * Метод получения атрибута для подтверждения авторизации
     *
     * @return атрибут src
     */
    @Step("Получение атрибута для подтверждения авторизации")
    public String getAttribute() {
        return downloadImg.getAttribute("src");
    }
}
