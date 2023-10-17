package pl.selenium.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static WebDriver getDriver(String name){
        if(name == "Firefox"){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();;
            return new ChromeDriver();
        }

    }
}
