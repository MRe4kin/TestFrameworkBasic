package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(xpath = "//*[@id=\"summary\"]")
    private WebElement summaaryArea;

    @FindBy(xpath = "//*[@id=\"description\"]")
    private WebElement descriptionArea;

    @FindBy(xpath = "//input[@class='btn btn-primary btn-white btn-round']")
    private WebElement submitIssueButton;


    @FindBy(xpath = "//div[@class='btn-group']/a[1]")
    private WebElement viewSubmittedIssuesButton;

    public ReportIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public void createIssues() {
        summaaryArea.sendKeys("test");
        descriptionArea.sendKeys("test");
        submitIssueButton.click();
        viewSubmittedIssuesButton.click();
    }

}