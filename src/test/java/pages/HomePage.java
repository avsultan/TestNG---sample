package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.TestBase;

public class HomePage extends PageBase {

    @FindBy(xpath = "(//*[@id = \"search\"])[3]")
    public WebElement searchBox;

    @FindBy(id = "search-icon-legacy")
    public WebElement searchButton;

    @FindBy(xpath = "(//a[@class = \"yt-simple-endpoint inline-block style-scope ytd-thumbnail\"])[1]")
    public WebElement firstThumbnail;

}
