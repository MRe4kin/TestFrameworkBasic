package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ReportIssues extends BaseTest {
    private MantisSite mantisSite;

    @Test
    public void createDeleteIssueTest() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToReportIssuesPage();
        Thread.sleep(3000);

        mantisSite.getReportIssuesPage().createIssues(); //Создание Issue

        String idSubmittedIssue = mantisSite.getReportIssuesPage().getIssueId(); //Получение Id Issue при создании

        mantisSite.getMainPage().goToViewIssuesPage(); // Переход на страницу просмотра Issue

        String summary = mantisSite.getViewIssuesPage().assertSummary(); //Получаем summary на странице ViewIssue
        String id = mantisSite.getViewIssuesPage().viewIssueBugId(); // Получаем ID Issue на странице ViewIssue

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(summary).contains("test"); //Создание проверка по summary
        softAssert.assertThat(idSubmittedIssue).contains(id); //Создание проверка по Id

        mantisSite.getViewIssuesPage().deleteIssue(); // Удаление Issue

        softAssert.assertThat(summary).doesNotContain("test"); //Удаление проверка по summary
        softAssert.assertThat(idSubmittedIssue).doesNotContain(id); //Удаление проверка по Id
    }
}
