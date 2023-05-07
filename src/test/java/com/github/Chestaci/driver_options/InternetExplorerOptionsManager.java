package com.github.Chestaci.driver_options;

import com.github.Chestaci.utils.ConfProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * Опции браузера Internet Explorer
 */
public class InternetExplorerOptionsManager {
    public InternetExplorerOptions getInternetExplorerOptions() {
        WebDriverManager.iedriver().arch32().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.withInitialBrowserUrl(ConfProperties.getProperty("sql_page"));
        return options;
    }
}
