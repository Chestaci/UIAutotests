package java.com.github.Chestaci.tests.way2automation_test;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.AlertPage;
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
 * Тесты работы с всплывающими оповещениями сайта www.way2automation.com
 */
@Epic("Тесты работы с всплывающими оповещениями сайта www.way2automation.com")
public class AlertTest {
    private WebDriver driver;
    private AlertPage alertPage;

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
        alertPage = new AlertPage(driver);
        driver.get(ConfProperties.getProperty("alert_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест работы с всплывающими оповещениями
     */
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Feature("Тест работы с всплывающими оповещениями")
    @Story("Тест перехода на всплывающее оповещение, ввода текста и получение ответного сообщения")
    public void alertTest() {
        alertPage.clickInputAlertButton();
        alertPage.clickAlertDemonstrationButton();
        driver.switchTo().alert().sendKeys(ConfProperties.getProperty("alert_page_name"));
        driver.switchTo().alert().accept();
        Assertions.assertThat(alertPage.getText()).contains(ConfProperties.getProperty("alert_page_name"));
    }
}
