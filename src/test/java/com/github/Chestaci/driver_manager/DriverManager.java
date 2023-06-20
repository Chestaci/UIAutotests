package com.github.Chestaci.driver_manager;

import com.github.Chestaci.factory_manager.FactoryManager;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.time.Duration;

/**
 * Класс, управляющий созданием и настройкой драйверов
 */
public class DriverManager {
    private DriverManager() {
    }

    public static WebDriver getPreparedDriver(String remote, String browserName) throws MalformedURLException {
        WebDriver webDriver = FactoryManager.getFactory(Boolean.parseBoolean(remote)).getWebDriver(browserName);
        long delaySeconds = 30;
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delaySeconds));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(delaySeconds));
        return webDriver;
    }
}
