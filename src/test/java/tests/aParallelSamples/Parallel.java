package tests.aParallelSamples;

import utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Parallel {

    @Test
    void test() {
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://www.google.com/");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chrome.quit();
    }



}
