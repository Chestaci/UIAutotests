package com.github.Chestaci.pages.way2automation;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект главной страницы
 */
public class MainPage extends Page {
    /**
     * Определение локатора футера страницы
     */
    @FindBy(css = "[class*=elementor-25361]")
    public WebElement footer;
    /**
     * Определение локатора элемента меню Resources
     */
    @FindBy(css = "#menu-item-27617")
    private WebElement menuResources;
    /**
     * Определение локатора заголовка страницы
     */
    @FindBy(xpath = "//*[@id=\"ast-desktop-header\"]/div[1]")
    private WebElement header;
    /**
     * Определение локатора элементов меню
     */
    @FindBy(css = "#ast-hf-menu-1")
    private WebElement menuList;
    /**
     * Определение локатора слайда №4
     */
    @FindBy(css = "#NzM0OjEzNA\\=\\=-1")
    private WebElement slideFour;

    /**
     * Определение локатора правой кнопки слайдера
     */
    @FindBy(css = "[class*=fa-angle-right]")
    private WebElement rightSliderButton;

    /**
     * Определение локатора кнопки перехода на страницу Practice Site 1
     */
    @FindBy(css = "#menu-item-27618>a")
    private WebElement practiceSiteOneButton;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод для осуществления проверки наличия заголовка
     */
    @Step("Проверка наличия заголовка")
    public boolean isHeaderPresent() {
        wait.until(ExpectedConditions.visibilityOf(header));
        return header.isDisplayed();
    }

    /**
     * Метод для осуществления проверки наличия футера
     */
    @Step("Проверка наличия футера")
    public boolean isFooterPresent() {
        wait.until(ExpectedConditions.visibilityOf(footer));
        return footer.isDisplayed();
    }

    /**
     * Метод для осуществления проверки наличия меню
     */
    @Step("Проверка наличия меню")
    public boolean isMenuPresent() {
        wait.until(ExpectedConditions.visibilityOf(menuList));
        return menuList.isDisplayed();
    }

    /**
     * Метод для осуществления проверки наличия слайда № 4
     */
    @Step("Проверка наличия слайда № 4")
    public boolean isSlideFourPresent() {
        wait.until(ExpectedConditions.visibilityOf(slideFour));
        return slideFour.isDisplayed();
    }

    /**
     * Метод для осуществления нажатия на меню Resources
     */
    @Step("Нажатие на меню Resources")
    public void clickMenuResources() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", menuResources);
        wait.until(ExpectedConditions.elementToBeClickable(menuResources));
        menuResources.click();
    }

    /**
     * Метод для осуществления нажатия на правую кнопку слайдера
     */
    @Step("Нажатие на правую кнопку слайдера")
    public void clickRightSliderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", rightSliderButton);
        wait.until(ExpectedConditions.elementToBeClickable(rightSliderButton));
        rightSliderButton.click();
    }

    /**
     * Метод для осуществления нажатия на кнопку перехода на Practice Site 1
     *
     * @see PracticeSiteOnePage
     */
    @Step("Нажатие на кнопку перехода на Practice Site 1")
    public PracticeSiteOnePage clickPracticeSiteOneButton() {
        wait.until(ExpectedConditions.visibilityOf(practiceSiteOneButton));
        practiceSiteOneButton.click();
        return new PracticeSiteOnePage(this.driver);
    }

    /**
     * Метод для осуществления прокрутки вниз на заданное количество пикселей
     *
     * @param pixel Количество пикселей
     */
    @Step("Прокрутка вниз на {pixel} пикселей")
    public void scroll(int pixel) {
        wait.until(ExpectedConditions.visibilityOf(menuList));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixel + ")");
    }

    /**
     * Метод для осуществления прокрутки вниз до нужного элемента на странице
     *
     * @param element Элемент на странице
     */
    @Step("Прокрутка вниз до элемента")
    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(menuList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
