package pages.dynamiccontrols;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.devtools.v85.io.IO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DynamicControlsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DynamicControlsTest extends BaseTest {

    private DynamicControlsPage dynamicControlsPage;

    @BeforeMethod
    public void initPage(){
        dynamicControlsPage = homePage.clickDynamicControlsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData){
        assertEquals(dynamicControlsPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), dynamicControlsPage.getHeaderTitle()));
    }

    @Test(testName = "Messag Can Be Enter", priority = 2, dataProvider = "getMessageToBeEntered")
    public void testMessageCanBeEnter(JSONObject testData){
        dynamicControlsPage.setTextInput(testData.getString("message"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException{
        return getTestDataFromJSON("test-data.dynamic-controls.header");
    }

    @DataProvider(name = "getMessageToBeEntered")
    public Object[][] getMessageToBeEntered() throws IOException{
        return getTestDataFromJSON("test-data.dynamic-controls.dynamic-controls");
    }
}
