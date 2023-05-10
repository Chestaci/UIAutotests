package com.github.Chestaci.pages.way2automation;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект страницы для перетаскивания элемента
 */
public class DragAndDropPage extends Page {

    /**
     * Определение локатора элемента для перетаскивания
     */
    @FindBy(css = "#draggable")
    private WebElement draggableElement;

    /**
     * Определение локатора принимающего элемента
     */
    @FindBy(css = "#droppable")
    private WebElement droppableElement;

    /**
     * Определение локатора рамки
     */
    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    /**
     * Определение локатора сообщения принимающего элемента
     */
    @FindBy(css = "#droppable>p")
    private WebElement text;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для перетаскивания элемента
     *
     * @return сообщение принимающего элемента
     */
    @Step("Перетаскивание элемента и получение сообщения принимающего элемента")
    public String dragAndDrop() {
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        action.dragAndDrop(draggableElement, droppableElement).perform();
        return text.getText();
    }
}
