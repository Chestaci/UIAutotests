package java.com.github.Chestaci.tests.way2automation_test;

import com.github.Chestaci.driver_manager.DriverManager;
import com.github.Chestaci.pages.way2automation.TabPage;
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
 * Тесты работы с вкладками сайта www.way2automation.com
 */
@Epic("Тесты работы с вкладками сайта www.way2automation.com")
public class TabTest {
    private WebDriver driver;
    private TabPage tabPage;

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
        tabPage = new TabPage(driver);
        driver.get(ConfProperties.getProperty("tab_page"));
    }

    /**
     * Завершающие действия после теста
     */
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    /**
     * Тест работы с вкладками
     */
    @Test
    @Severity(value = SeverityLevel.NORMAL)
    @Feature("Тест работы с вкладками")
    @Story("Тест перехода на разные вкладки")
    public void tabTest() {
        Assertions.assertThat(tabPage.tabClick()).isEqualTo(3);
    }
}
