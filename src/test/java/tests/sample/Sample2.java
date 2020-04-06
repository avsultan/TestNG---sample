package tests.sample;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.HomePage;
import testBase.TestBase;
import utilities.BrowserUtils;
import utilities.Driver;

import static org.testng.Assert.assertEquals;

public class Sample2 extends TestBase {

    WebDriver driver;
    BrowserUtils bu;
    HomePage hp;

    @Test(groups = "samples")
    void sample3() {
        logger = extentReport.createTest("sample3");
    }

    @Test(groups = "samples")
    void sample4() {
        logger = extentReport.createTest("sample4");
    }

    @Test(groups = "samples")
    void sample5() {
        logger = extentReport.createTest("sample5");
    }

}
