package pages.javascriptalerts;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.JavaScriptAlertsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class JavaScriptAlertsTest extends BaseTest {

    private JavaScriptAlertsPage javaScriptAlertsPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("JavaScript Alerts");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        javaScriptAlertsPage = homePage.clickJavaScriptAlertsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle",  groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = javaScriptAlertsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "JavaScript Alert", priority = 2, dataProvider = "javaScriptAlertData",  groups = {"regression"})
    public void testJavaScriptAlert(JSONObject testData) {
        javaScriptAlertsPage.clickAlert();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        String expectedResult = testData.getString("label_text");
        String expectedMessage = testData.getString("alert_message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
        assertEquals(actualResult, expectedResult,
                onFailure(expectedResult, actualResult));
    }

    @DataProvider(name = "javaScriptAlertData")
    private Object[][] javaScriptAlertData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("alert"));
    }

    @Test(testName = "JavaScript Alert Confirm OK", priority = 3, dataProvider = "javaScriptConfirmOKData", groups = {"regression"})
    public void testJavaScriptAlertConfirmOK(JSONObject testData) {
        javaScriptAlertsPage.clickConfirm();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        String expectedResult = testData.getString("label_text");
        String expectedMessage = testData.getString("alert_message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
        assertEquals(actualResult, expectedResult,
                onFailure(expectedResult, actualResult));
    }

    @DataProvider(name = "javaScriptConfirmOKData")
    private Object[][] javaScriptConfirmOKData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("confirm-ok"));
    }

    @Test(testName = "JavaScript Alert Confirm Cancel", priority = 4, dataProvider = "javaScriptConfirmCancelData", groups = {"regression"})
    public void testJavaScriptAlertConfirmCancel(JSONObject testData) {
        javaScriptAlertsPage.clickConfirm();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickDismissAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        String expectedResult = testData.getString("label_text");
        String expectedMessage = testData.getString("alert_message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
        assertEquals(actualResult, expectedResult,
                onFailure(expectedResult, actualResult));
    }

    @DataProvider(name = "javaScriptConfirmCancelData")
    private Object[][] javaScriptConfirmCancelData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("confirm-cancel"));
    }

    @Test(testName = "JavaScript Alert Text Prompt", priority = 5, dataProvider = "javaScriptAlertTextPromptData", groups = {"regression"})
    public void testJavaScriptAlertTextPrompt(JSONObject testData) {
        javaScriptAlertsPage.clickPrompt();
        String actualMessage = testData.getString("alert_text");
        javaScriptAlertsPage.setAlertText(actualMessage);
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        String expectedMessage = testData.getString("label_text");
        assertEquals(actualResult, expectedMessage,
                onFailure(expectedMessage, actualResult));
    }

    @DataProvider(name = "javaScriptAlertTextPromptData")
    private Object[][] javaScriptAlertTextPromptData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("prompt"));
    }

}
