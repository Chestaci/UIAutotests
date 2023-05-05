package com.github.Chestaci.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

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
   //     options.addArguments("--headless=new");
        return options;
        }
    private static void setUpDriver(WebDriver driver) {
        long delaySeconds = 30;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delaySeconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(delaySeconds));
    }
    @Parameters({"remote"})
    public static WebDriver getPreparedDriver(String remote) throws MalformedURLException {
        WebDriver webDriver = null;
        if(remote.equals("true")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.WINDOWS);
            webDriver = new RemoteWebDriver(new URL(ConfProperties.getProperty("localhost")), capabilities);
        }else {
            webDriver = new ChromeDriver(getChromeOptions());
        }
        setUpDriver(webDriver);
        return webDriver;
    }
}
