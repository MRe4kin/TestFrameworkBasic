package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ReportIssues extends BaseTest {
    private MantisSite mantisSite;

    @Test
    public void createDeleteIssueTest() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");

        mantisSite.getMainPage().goToReportIssuesPage();

        String idCreatedIssue = mantisSite.getReportIssuesPage().createIssues(); //Создание Issue

        mantisSite.getMainPage().goToViewIssuesPage(); // Переход на страницу просмотра Issue

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(mantisSite.getViewIssuesPage().assertSummary()).contains("test"); //Создание проверка по summary
        softAssert.assertThat(mantisSite.getViewIssuesPage().viewIssueBugId()).contains(idCreatedIssue); //Создание проверка по id
        softAssert.assertAll();

        mantisSite.getViewIssuesPage().deleteIssue(); // Удаление Issue

        softAssert.assertThat(mantisSite.getViewIssuesPage().assertSummary()).doesNotMatch("test"); //Удаление проверка по summary
        softAssert.assertThat(mantisSite.getViewIssuesPage().viewIssueBugId()).doesNotMatch(idCreatedIssue); //Удаление проверка по id
        softAssert.assertAll();
    }
}
