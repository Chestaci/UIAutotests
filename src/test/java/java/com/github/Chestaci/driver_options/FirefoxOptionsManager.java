package java.com.github.Chestaci.driver_options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

/**
 * Опции браузера Firefox
 */
public class FirefoxOptionsManager {
    public FirefoxOptions getFirefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
        }});
        return options;
    }
}
