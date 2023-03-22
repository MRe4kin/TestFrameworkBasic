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

        mantisSite.getReportIssuesPage().createIssues(); //Создание Issue

        String idSubmittedIssue = mantisSite.getReportIssuesPage().getIssueId(); //Получение Id Issue при создании

        mantisSite.getMainPage().goToViewIssuesPage(); // Переход на страницу просмотра Issue

        String summary = mantisSite.getViewIssuesPage().assertSummary(); //Получаем summary на странице ViewIssue
        String id = mantisSite.getViewIssuesPage().viewIssueBugId(); // Получаем ID Issue на странице ViewIssue

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(summary).contains("test"); //Создание проверка по summary
        softAssert.assertThat(idSubmittedIssue).contains(id); //Создание проверка по Id
        softAssert.assertAll();

        mantisSite.getViewIssuesPage().deleteIssue(); // Удаление Issue

        softAssert.assertThat(summary).doesNotMatch("test"); //Удаление проверка по summary
        softAssert.assertThat(idSubmittedIssue).doesNotMatch(id); //Удаление проверка по Id
        softAssert.assertAll(); //При добавлении assertAll проверки после удаления Issue валятся. В чем может быть дело? Не правильно составлен сам softAssert? Или составлен верно, но я не верно им пользуюсь?
    }
}
