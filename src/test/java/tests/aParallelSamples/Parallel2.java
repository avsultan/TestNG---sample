package tests.aParallelSamples;

import utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Parallel2 {

    @Test
    void test2() {
        WebDriverManager.chromedriver().version("79.0").setup();
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://www.youtube.com/");
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chrome.quit();
    }

}
