package com.github.Chestaci.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Класс Listener для прикрепления вложений в отчет
 */
public class TestListener implements ITestListener {

    /**
     * Метод получения текстового вложения
     */
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    /**
     * Метод прикрепления скриншота в результате падения теста
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        saveScreenshotPNGAshot(driver);
    }

    /**
     * Метод получения скриншота с помощью Selenium
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNGSelenium(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * Метод получения скриншота с помощью aShot
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNGAshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot.getImage(), "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }
}