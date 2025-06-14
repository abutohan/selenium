package pages.disappearingelements;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DisappearingElementsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DisappearingElementsTest extends BaseTest {

    private DisappearingElementsPage disappearingElementsPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Disappearing Element");
    }

    @BeforeMethod
    public void initPage(){
        disappearingElementsPage = homePage.clickDisappearingElementsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData){
        String actualHeaderTitle = disappearingElementsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Verify Existence of Element", priority = 2, dataProvider = "getElementData")
    public void testExistenceOfElement(JSONObject testData){
        disappearingElementsPage.chekPresenceOfElementLink(testData.getString("element"));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.disappearing-elements.header");
    }

    @DataProvider(name = "getElementData")
    private Object[][] getElementData() throws IOException {
        return getTestDataFromJSON("test-data.disappearing-elements.elements");
    }

}
