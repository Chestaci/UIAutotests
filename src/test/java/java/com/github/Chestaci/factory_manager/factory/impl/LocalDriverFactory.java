package java.com.github.Chestaci.factory_manager.factory.impl;

import com.github.Chestaci.driver_options.ChromeOptionsManager;
import com.github.Chestaci.driver_options.EdgeOptionsManager;
import com.github.Chestaci.driver_options.FirefoxOptionsManager;
import com.github.Chestaci.driver_options.InternetExplorerOptionsManager;
import com.github.Chestaci.driver_options.OperaOptionsManager;
import com.github.Chestaci.factory_manager.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Фабрика, поставляющая драйвера для локального запуска
 */
public class LocalDriverFactory implements DriverFactory {
    @Override
    public WebDriver getWebDriver(String browserName) {
        return switch (browserName) {
            case "firefox" -> new FirefoxDriver(new FirefoxOptionsManager().getFirefoxOptions());
            case "chrome" -> new ChromeDriver(new ChromeOptionsManager().getChromeOptions());
            case "MicrosoftEdge" -> new EdgeDriver(new EdgeOptionsManager().getEdgeOptions());
            case "internet explorer" ->
                    new InternetExplorerDriver(new InternetExplorerOptionsManager().getInternetExplorerOptions());
            case "opera" -> new ChromeDriver(new OperaOptionsManager().getOperaOptions());
            default -> throw new IllegalStateException("Unexpected value: " + browserName);
        };
    }
}
