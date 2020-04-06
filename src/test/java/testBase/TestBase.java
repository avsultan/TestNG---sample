package testBase;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.ConfigsReader;
import utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    protected WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    //actual report
    protected static ExtentReports extentReport;
    //html file
    protected ExtentHtmlReporter htmlReporter;
    //each test (@Test - annotation) with logs(details)
    protected ExtentTest logger;

    //each report will be stored in different folder
    private final String dateForFolder = new SimpleDateFormat("yyyy-MM-dd - HH-mm-ss").format(Calendar.getInstance().getTime());
    private String reportPath = System.getProperty("user.dir") + "/test - output/" +
            dateForFolder + "/report.html";

    @BeforeSuite(alwaysRun = true)
    public void setUpTest() {
        //initializing actual report
        extentReport = new ExtentReports();
        //initializing html and storing it in - reportPath
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //attach extentReport
        extentReport.attachReporter(htmlReporter);

        //report info
        extentReport.setSystemInfo("browser", ConfigsReader.getProperty("browser"));
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));

        //html info
        htmlReporter.config().setReportName("Sample framework");
        htmlReporter.config().setDocumentTitle("Sample framework");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        //this line will add another test in report
        extentReport.flush();

        //Driver.closeDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() {
        //initializing driver
        driver = Driver.getDriver();

        //initializing explicit wait
        //wait = new WebDriverWait(driver, 30);

        //implicit wait(optional)
        /*driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);*/

        //initializing actions
        actions = new Actions(driver);

        //maximizing the browser window
        driver.manage().window().maximize();

        //navigating browser to url from configs
        driver.get(ConfigsReader.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) {
        //if test failed print name of the test and exceptions, take screenshot
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.fail(result.getName());
            logger.fail(result.getThrowable());
            try {
                logger.addScreenCaptureFromPath(new BrowserUtils().takeScreenshot(result.getName(), dateForFolder));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //if skipped print name of the test + " - test is skipped"
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.skip(result.getName() + " - test is skipped");
            //if passed print name of the test + " - passed"
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            logger.pass(result.getName() + " - passed");
        }

        //closing driver after each test(method)
        Driver.closeDriver();
    }

}
