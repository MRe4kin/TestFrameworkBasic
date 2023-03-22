package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//*[@id='buglist']//tr[1]/td[4]")
    private WebElement issueId;

    @FindBy(xpath = "//input[@value='Delete']")
    private WebElement deleteIssueButton;

    @FindBy(xpath = "//input[@value='Delete Issues']")
    private WebElement confirmDeleteIssueButton;

    @FindBy(xpath = "//*[@id='buglist']//tr[1]/td[11]")
    private WebElement summaryAssert;

    @FindBy(xpath = "//*[@id='buglist']//tr[1]/td[4]")
    private WebElement idAssert;

    public ViewIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public String assertSummary() {
        return summaryAssert.getText();

    }

    public void deleteIssue() {
        issueId.click();
        deleteIssueButton.click();
        confirmDeleteIssueButton.click();

    }

    public String viewIssueBugId() {
        return idAssert.getText();
    }
}
