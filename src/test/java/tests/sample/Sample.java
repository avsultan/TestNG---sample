package tests.sample;

import pages.HomePage;
import utilities.BrowserUtils;
import utilities.Driver;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import testBase.TestBase;

public class Sample extends TestBase {

    WebDriver driver;
    BrowserUtils bu;
    HomePage hp;

    @Test()
    void sample() {
        logger = extentReport.createTest("sample");

        driver = Driver.getDriver();
        bu = new BrowserUtils();
        hp = new HomePage();

        assertEquals(driver.getTitle(), "YouTube");
        hp.searchBox.sendKeys("miyagi kosandra");
        hp.searchButton.click();
    }

    @Test
    void sample2() {
        logger = extentReport.createTest("sample2");

        driver = Driver.getDriver();
        bu = new BrowserUtils();
        hp = new HomePage();

        assertEquals(driver.getTitle(), "YouTube");
        bu.moveMouseTo(hp.firstThumbnail);
        bu.waitInSeconds(5);
    }



}
