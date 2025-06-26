package pages.notificationmessages;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NotificationMessagesPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class NotificationMessagesTest extends BaseTest {

    private NotificationMessagesPage notificationMessagesPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Notification Messages");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        notificationMessagesPage = homePage.clickNotificationMessagesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = notificationMessagesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Notification Message Successful", priority = 2, dataProvider = "getNotificationMessageData", groups = {"regression"})
    public void testNotificationMessages(JSONObject testData) {
        String message = testData.getString("notification_message");
        assertTrue(notificationMessagesPage.checkNotificationMessage(message));
    }

    @DataProvider(name = "getNotificationMessageData")
    private Object[][] getNotificationMessageData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("message"));
    }

}
