package pages.entryad;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EntryAdPage;

import java.io.IOException;

import static org.testng.Assert.*;
import static utils.ReadJSON.getTestDataFromJSON;

public class EntryAdTest extends BaseTest {

    private EntryAdPage entryAdPage;

    @BeforeMethod
    public void initPage() {
        entryAdPage = homePage.clickEntryAdPage();
    }
    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(entryAdPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), entryAdPage.getHeaderTitle()));
    }

    @Test(testName = "Check Modal Presence", priority = 2)
    public void testModalPresence(){
        assertTrue(entryAdPage.checkModalPresence());
        entryAdPage.clickCloseModal();
        assertFalse(entryAdPage.checkModalPresence());
        getWindowManager().refreshPage();
        assertFalse(entryAdPage.checkModalPresence());
        entryAdPage.clickRestart();
        getWindowManager().refreshPage();
        assertTrue(entryAdPage.checkModalPresence());
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.entry-ad.header");
    }


}
