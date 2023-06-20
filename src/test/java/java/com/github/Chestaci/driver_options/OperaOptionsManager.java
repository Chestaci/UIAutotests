package java.com.github.Chestaci.driver_options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

/**
 * Опции браузера Opera
 */
public class OperaOptionsManager {
    public ChromeOptions getOperaOptions() {
        WebDriverManager.operadriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVNC", true);
        }});
        return options;
    }
}
