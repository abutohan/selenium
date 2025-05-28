package pages.disappearingelements;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DisappearingElementsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DisappearingElementsTest extends BaseTest {

    private DisappearingElementsPage disappearingElementsPage;

    @BeforeMethod
    public void initPage(){
        disappearingElementsPage = homePage.clickDisappearingElementsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData){
        assertEquals(disappearingElementsPage.getHeaderTitle(), "Disappearing Elements",
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), disappearingElementsPage.getHeaderTitle()));
    }

    @Test(testName = "Verify Existence of Element", priority = 2, dataProvider = "getElement")
    public void testExistenceOfElement(JSONObject testData){
        disappearingElementsPage.chekPresenceOfElementLink(testData.getString("element"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.disappearing-elements.header");
    }

    @DataProvider(name = "getElement")
    public Object[][] getElement() throws IOException {
        return getTestDataFromJSON("test-data.disappearing-elements.disappearing-elements");
    }
}
