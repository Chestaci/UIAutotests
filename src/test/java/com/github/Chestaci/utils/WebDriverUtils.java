package com.github.Chestaci.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Утилитный класс для настроек WebDriver
 */
public class WebDriverUtils {
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");
        options.addArguments("--headless=new");
        return options;
        }
    private static void setUpDriver(WebDriver driver) {
        long delaySeconds = 30;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delaySeconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(delaySeconds));
    }
    public static WebDriver getPreparedDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);
        WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilities);
        setUpDriver(webDriver);
        return webDriver;
    }
}
