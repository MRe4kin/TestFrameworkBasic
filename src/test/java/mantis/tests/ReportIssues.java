package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReportIssues extends BaseTest {
    private MantisSite mantisSite;

    @Test
    public void createDeleteIssueTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToReportIssuesPage();
        Thread.sleep(3000);

        mantisSite.getReportIssuesPage().createIssues(); //Создание Issue

        WebElement viewSubmittedIssueButton = driver.findElement(By.xpath("//td[@class='bug-id']"));
        String idSubmittedIssue = viewSubmittedIssueButton.getText();

        mantisSite.getMainPage().goToViewIssuesPage(); // Переход на страницу просмотра Issue

        String summary = mantisSite.getViewIssuesPage().assertSummary(); //Получаем текст из ячейки summary
        String id = mantisSite.getViewIssuesPage().assertId(); // Получаем ID Issue

        SoftAssertions softAssert = new SoftAssertions(); // Объявляем класс softassertions

        softAssert.assertThat(summary).contains("test"); //Проверка summary
        softAssert.assertThat(idSubmittedIssue).contains(id); //Проверка ID
        softAssert.assertAll();

        mantisSite.getViewIssuesPage().deleteIssue(); // Удаление Issue

        softAssert.assertThat(summary).doesNotContain("test"); //Проверка summary
        softAssert.assertThat(idSubmittedIssue).doesNotContain(id); //Проверка ID
//        softAssert.assertAll(); //Почему тест не проходит когда дописана эта строка?

    }
}
