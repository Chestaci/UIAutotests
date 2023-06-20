package java.com.github.Chestaci.pages.way2automation;

import com.github.Chestaci.pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Объект домашней страницы пользователя
 */
public class HomePage extends Page {

    /**
     * Определение локатора приветствия
     */
    @FindBy(xpath = "//*[contains(text(), 'logged in')]")
    private WebElement text;

    /**
     * Конструктор класса, занимающийся инициализацией полей класса
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Метод получения приветственного сообщения
     *
     * @return приветственное сообщение
     */
    @Step("Получение приветственного сообщения")
    public String getWelcomeMessage() {
        wait.until(ExpectedConditions.visibilityOf(text));
        return text.getText();
    }
}
