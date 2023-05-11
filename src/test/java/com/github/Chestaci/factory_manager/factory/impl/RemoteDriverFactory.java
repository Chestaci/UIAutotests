package com.github.Chestaci.factory_manager.factory.impl;

import com.github.Chestaci.driver_options.ChromeOptionsManager;
import com.github.Chestaci.driver_options.EdgeOptionsManager;
import com.github.Chestaci.driver_options.FirefoxOptionsManager;
import com.github.Chestaci.driver_options.InternetExplorerOptionsManager;
import com.github.Chestaci.driver_options.OperaOptionsManager;
import com.github.Chestaci.factory_manager.factory.DriverFactory;
import com.github.Chestaci.utils.ConfProperties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Фабрика, поставляющая драйвера для удалённого запуска
 */
public class RemoteDriverFactory implements DriverFactory {
    @Override
    public WebDriver getWebDriver(String browserName) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browserName) {
            case "firefox" -> {
                capabilities.setBrowserName(Browser.FIREFOX.browserName());
                capabilities.setPlatform(Platform.WINDOWS);
                FirefoxOptions firefoxOptions = new FirefoxOptionsManager().getFirefoxOptions();
                firefoxOptions.merge(capabilities);

                return new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), firefoxOptions);
            }
            case "chrome" -> {
                capabilities.setBrowserName(Browser.CHROME.browserName());
                capabilities.setPlatform(Platform.WINDOWS);
                ChromeOptions chromeOptions = new ChromeOptionsManager().getChromeOptions();
                chromeOptions.merge(capabilities);

                return new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), chromeOptions);
            }
            case "MicrosoftEdge" -> {
                capabilities.setBrowserName(Browser.EDGE.browserName());
                capabilities.setPlatform(Platform.WINDOWS);
                EdgeOptions edgeOptions = new EdgeOptionsManager().getEdgeOptions();
                edgeOptions.merge(capabilities);

                return new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), edgeOptions);
            }
            case "internet explorer" -> {
                capabilities.setBrowserName(Browser.IE.browserName());
                capabilities.setPlatform(Platform.WINDOWS);
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptionsManager().getInternetExplorerOptions();
                internetExplorerOptions.merge(capabilities);

                return new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), internetExplorerOptions);
            }
            case "opera" -> {
                capabilities.setBrowserName(Browser.OPERA.browserName());
                capabilities.setPlatform(Platform.WINDOWS);
                ChromeOptions operaOptions = new OperaOptionsManager().getOperaOptions();
                operaOptions.merge(capabilities);

                return new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), operaOptions);
            }
            default -> throw new IllegalStateException("Unexpected value: " + browserName);
        }
    }
}
