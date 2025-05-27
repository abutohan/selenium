package pages.contextmenu;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContextMenuPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class ContextMenuTest extends BaseTest {

    private ContextMenuPage contextMenuPage;

    @BeforeMethod
    public void initPage() {
        contextMenuPage = homePage.clickContextMenuPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(contextMenuPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), contextMenuPage.getHeaderTitle()));
    }

    @Test(testName = "Right Click Context Menu", priority = 2, dataProvider = "getAlertMessage")
    public void testRightClickContextMenu(JSONObject testData) {
        contextMenuPage.rightClickTheBox();
        assertEquals(contextMenuPage.getPopUpText(), testData.getString("alert_message"),
                String.format("Expected: %s - Actual: %s", testData.getString("alert_message"), contextMenuPage.getPopUpText()));
        contextMenuPage.acceptPopUp();

    }


    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.context-menu.header");
    }

    @DataProvider(name = "getAlertMessage")
    public Object[][] getAlertMessage() throws IOException {
        return getTestDataFromJSON("test-data.context-menu.context-menu");
    }
}
