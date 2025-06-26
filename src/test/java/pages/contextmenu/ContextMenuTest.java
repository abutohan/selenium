package pages.contextmenu;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContextMenuPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class ContextMenuTest extends BaseTest {

    private ContextMenuPage contextMenuPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Context Menu");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        contextMenuPage = homePage.clickContextMenuPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = contextMenuPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Right Click Context Menu", priority = 2, dataProvider = "getAlertMessageData", groups = {"regression"})
    public void testRightClickContextMenu(JSONObject testData) {
        contextMenuPage.rightClickTheBox();
        String actualAlertMessage = contextMenuPage.getPopUpText();
        String expectedAlertMessage = testData.getString("alert_message");
        assertEquals(actualAlertMessage, expectedAlertMessage,
                onFailure(expectedAlertMessage, actualAlertMessage));
        contextMenuPage.acceptPopUp();
    }

    @DataProvider(name = "getAlertMessageData")
    private Object[][] getAlertMessageData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("alert-message"));
    }

}
