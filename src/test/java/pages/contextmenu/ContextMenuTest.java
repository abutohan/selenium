package pages.contextmenu;

import base.BaseTest;
import org.json.JSONObject;
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

    @BeforeMethod
    public void initPage() {
        contextMenuPage = homePage.clickContextMenuPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = contextMenuPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Right Click Context Menu", priority = 2, dataProvider = "getAlertMessageData")
    public void testRightClickContextMenu(JSONObject testData) {
        contextMenuPage.rightClickTheBox();
        String actualAlertMessage = contextMenuPage.getPopUpText();
        String expectedAlertMessage = testData.getString("alert_message");
        assertEquals(actualAlertMessage, expectedAlertMessage,
                onFailure(expectedAlertMessage, actualAlertMessage));
        contextMenuPage.acceptPopUp();
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.context-menu.header");
    }

    @DataProvider(name = "getAlertMessageData")
    private Object[][] getAlertMessageData() throws IOException {
        return getTestDataFromJSON("test-data.context-menu.alert-message");
    }

}
