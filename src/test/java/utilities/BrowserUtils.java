package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BrowserUtils {

    private WebDriver driver = Driver.getDriver();
    WebDriverWait wait = TestBase.wait;
    Actions actions = TestBase.actions;



    //fluentWait
    /*FluentWait fluentWait = new FluentWait<WebDriver>(driver)
            .withTimeout(30, TimeUnit.SECONDS)
            .pollingEvery(500, TimeUnit.MILLISECONDS)
            .ignoring(StaleElementReferenceException.class)
            .ignoring(NoSuchElementException.class)
            .ignoring(ElementNotVisibleException.class);;*/

    JavascriptExecutor jse = (JavascriptExecutor) driver;

    //----------------------------------------------------------------------------------------------------------------//

    // - screenshot
    public String takeScreenshot(String methodName, String folderName) {
        //taking screenshot
        File file = ((TakesScreenshot) Driver.getDriver()) .getScreenshotAs(OutputType.FILE);
        //date and time for the screenshot
        String dateANDtime = new SimpleDateFormat("yyyy-MM-dd - HH-mm-ss").format(Calendar.getInstance().getTime());
        //path where screenshot will be stored
        String path = System.getProperty("user.dir") + "/test - output/" + folderName + "/";
        //format of the screenshot - could be .jpg, .bmp, .gif etc.
        String fileFormat = ".png";

        //saving screenshot
        try {
            FileUtils.copyFile(file, new File(path + methodName + " " + dateANDtime + fileFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path + methodName + " " + dateANDtime + fileFormat;
    }

    //takes you forward by one page on the browser’s history
    public void goForward() {
        driver.navigate().forward();
    }

    //takes you backward by one page on the browser’s history
    public void goBack() {
        driver.navigate().back();
    }

    //refresh the current page
    public void refresh() { driver.navigate().refresh(); }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void get(String url) {
        driver.get(url);
    }

    //i-frames
    public void switchToIFrameByElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void switchToIFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToIFrameByName(String name) {
        driver.switchTo().frame(name);
    }

    public void switchToParentIFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToMainDocument() {
        driver.switchTo().defaultContent();
    }

    //tabs
    public void openNewTabAndSwitchToIt(String urlNewTab) {
        jse.executeScript("window.open()");
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String currentWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandles.get(windowHandles.indexOf(currentWindowHandle) + 1));
        driver.get(urlNewTab);
    }

    public void switchToNextTab() {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String currentWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandles.get(windowHandles.indexOf(currentWindowHandle) + 1));
    }

    public void switchToPreviousTab() {
        ArrayList<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String currentWindowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandles.get(windowHandles.indexOf(currentWindowHandle) - 1));
    }

    public void switchToTab(String tabName) {
        driver.switchTo().window(tabName);
    }

    //dropDowns
    public void selectFromDropdownByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.selectByValue(value);
    }

    public void selectFromDropdownByText(WebElement dropdownElement, String text) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }

    public void selectFromDropdownByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }

    public void deSelectFromDropdownByValue(WebElement dropdownElement, String value) {
        Select select = new Select(dropdownElement);
        select.deselectByValue(value);
    }

    public void deSelectFromDropdownByText(WebElement dropdownElement, String text) {
        Select select = new Select(dropdownElement);
        select.deselectByVisibleText(text);
    }

    public void deSelectFromDropdownByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.deselectByIndex(index);
    }

    public void deSelectFromDropdownAll(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        select.deselectAll();
    }

    public WebElement getFirstSelectedOptionFromDropdown(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption();
    }

    public List<WebElement> getAllSelectedOptionsFromDropdown(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        return select.getAllSelectedOptions();
    }

    public List<WebElement> getOptionsFromDropdown(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        return select.getOptions();
    }


    // - wait
    public void waitInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilAlertPop() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementsAreVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilPageIsLoaded() {
        wait.until(driver -> jse.executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilAttributeContains(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public void waitUntilAttributeToBe(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public void waitUntilAttributeNotEmpty(WebElement element, String attribute) {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public void waitUntilElementSelectionState(WebElement element, boolean selected) {
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, selected));
    }

    public void waitUntilElementIsSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitUntilFrameAvailableAndSwitchToIt(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void waitUntilElementIsSelected(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    //actions
    public void moveMouseTo(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void click(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    //alerts
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getTextAlert() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendKeysAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    //elements
    public void clearInput(WebElement element) {
        element.clear();
    }

    public boolean elementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean elementIsEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean elementIsSelected(WebElement element) {
        return element.isSelected();
    }

    public void elementClick(WebElement element) {
        element.click();
    }

    public String getTextElement(WebElement element) {
        return element.getText();
    }

    public String getAttributeElement(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    //page
    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}
