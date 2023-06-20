package java.com.github.Chestaci.pages.way2automation;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

/**
 * Объект страницы для работы с вкладками
 */
public class TabPage extends Page {

    /**
     * Определение локатора ссылки
     */
    @FindBy(xpath = "//a[text()='New Browser Tab'] ")
    private WebElement link;

    /**
     * Определение локатора рамки
     */
    @FindBy(css = ".demo-frame")
    private WebElement iframe;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public TabPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод нажатия на ссылку в разных вкладках
     *
     * @return количество открытых вкладок
     */
    @Step("Нажатие на ссылку в первой вкладке, переход на вторую вкладку, нажатие на ссылку во второй вкладке.")
    public int tabClick() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        link.click();
        //Получение списка вкладок
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        //Переключение на вторую вкладку в браузере
        driver.switchTo().window(tabs.get(1));
        link.click();
        return driver.getWindowHandles().size();
    }
}
