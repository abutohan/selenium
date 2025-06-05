package pages.javascriptalerts;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.JavaScriptAlertsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class JavaScriptAlertsTest extends BaseTest {

    private JavaScriptAlertsPage javaScriptAlertsPage;

    @BeforeMethod
    public void initPage() {
        javaScriptAlertsPage = homePage.clickJavaScriptAlertsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(javaScriptAlertsPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), javaScriptAlertsPage.getHeaderTitle()));
    }

    @Test(testName = "JavaScript Alert", priority = 2, dataProvider = "javaScriptAlertData")
    public void testJavaScriptAlert(JSONObject testData) {
        javaScriptAlertsPage.clickAlert();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        assertEquals(actualMessage, testData.getString("alert_message"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("alert_message"), actualMessage));
        assertEquals(actualResult, testData.getString("label_text"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("label_text"), actualResult));
    }


    @Test(testName = "JavaScript Alert Confirm OK", priority = 3, dataProvider = "javaScriptConfirmOKData")
    public void testJavaScriptAlertConfirmOK(JSONObject testData) {
        javaScriptAlertsPage.clickConfirm();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        assertEquals(actualMessage, testData.getString("alert_message"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("alert_message"), actualMessage));
        assertEquals(actualResult, testData.getString("label_text"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("label_text"), actualResult));
    }

    @Test(testName = "JavaScript Alert Confirm Cancel", priority = 4, dataProvider = "javaScriptConfirmCancelData")
    public void testJavaScriptAlertConfirmCancel(JSONObject testData) {
        javaScriptAlertsPage.clickConfirm();
        String actualMessage = javaScriptAlertsPage.getAlertText();
        javaScriptAlertsPage.clickDismissAlert();
        String actualResult = javaScriptAlertsPage.getResultText();
        assertEquals(actualMessage, testData.getString("alert_message"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("alert_message"), actualMessage));
        assertEquals(actualResult, testData.getString("label_text"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("label_text"), actualResult));
    }

    @Test(testName = "JavaScript Alert Text Prompt", priority = 4, dataProvider = "javaScriptAlertTextPromptData")
    public void testJavaScriptAlertTextPrompt(JSONObject testData) {
        javaScriptAlertsPage.clickPrompt();
        String actualMessage = testData.getString("alert_text");
        javaScriptAlertsPage.setAlertText(actualMessage);
        javaScriptAlertsPage.clickAcceptAlert();
        String actualResult = javaScriptAlertsPage.getResultText();


        assertEquals(actualResult, testData.getString("label_text"),
                String.format("Incorrect result: Expected '%s', but got '%s'", testData.getString("label_text"), actualResult));
    }


    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.javascript-alerts.header");
    }

    @DataProvider(name = "javaScriptAlertData")
    private Object[][] javaScriptAlertData() throws IOException {
        return getTestDataFromJSON("test-data.javascript-alerts.alert");
    }

    @DataProvider(name = "javaScriptConfirmOKData")
    private Object[][] javaScriptConfirmOKData() throws IOException {
        return getTestDataFromJSON("test-data.javascript-alerts.confirm-ok");
    }

    @DataProvider(name = "javaScriptConfirmCancelData")
    private Object[][] javaScriptConfirmCancelData() throws IOException {
        return getTestDataFromJSON("test-data.javascript-alerts.confirm-cancel");
    }

    @DataProvider(name = "javaScriptAlertTextPromptData")
    private Object[][] javaScriptAlertTextPromptData() throws IOException {
        return getTestDataFromJSON("test-data.javascript-alerts.prompt");
    }

}
