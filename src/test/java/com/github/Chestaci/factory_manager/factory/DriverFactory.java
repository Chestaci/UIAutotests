package com.github.Chestaci.factory_manager.factory;

import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

/**
 * Интерфейс фабрики драйверов
 */
public interface DriverFactory {
    WebDriver getWebDriver(String browserName) throws MalformedURLException;
}
