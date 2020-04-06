package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

    private static WebDriver driver;

    //method returns driver
    public static WebDriver getDriver() {
        if (driver == null) {
            //switch statement(browser value from configs)
            switch (ConfigsReader.getProperty("browser")) {
                //if browser value from configs equals to chrome
                case "chrome":
                    //setting up chrome driver in pc
                    WebDriverManager.chromedriver().version("79.0").setup();
                    //initializing driver
                    driver = new ChromeDriver();
                    break;

                //if browser value from configs equals to firefox
                case "firefox":
                    //setting up firefox driver in pc
                    WebDriverManager.firefoxdriver().setup();
                    //initializing driver
                    driver = new FirefoxDriver();
                    break;

                //if browser value from configs equals to ie(internet explorer)
                case "ie":
                    //setting up internet explorer driver in pc
                    WebDriverManager.iedriver().setup();
                    //initializing driver
                    driver = new InternetExplorerDriver();
                    break;
            }
        }
        return driver;
    }


    //close driver and make it equal to null
    public static void closeDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }

}