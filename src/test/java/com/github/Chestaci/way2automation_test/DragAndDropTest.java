package com.github.Chestaci.way2automation_test;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.DragAndDropPage;
import com.github.Chestaci.utils.ConfProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Тесты работы перетаскивания элемента сайта www.way2automation.com
 */
@Epic("Тесты работы перетаскивания элемента сайта www.way2automation.com")
public class DragAndDropTest {
    private WebDriver driver;
    private DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public void setUpMethod(final ITestContext context) {
        context.setAttribute("driver", driver);
    }

    /**
     * Инициализация перед началом теста
     */
    @BeforeTest
    @Parameters({"remote", "browserName"})
    void setUpTest(@Optional("false") String remote, @Optional("chrome") String browserName) throws MalformedURLException {
        driver = DriverManager.getPreparedDriver(remote, browserName);
        dragAndDropPage = new DragAndDropPage(driver);
        driver.get(ConfProperties.getProperty("drag_and_drop_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест перетаскивания элемента
     */
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест перетаскивания элемента")
    @Story("Тест перетаскивания элемента и получения сообщения")
    public void dragAndDropTest() {
        Assertions.assertThat(dragAndDropPage.dragAndDrop()).isEqualTo("Dropped!");
    }
}
