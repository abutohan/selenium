package pages.horizontalslider;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HorizontalSliderPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class HorizontalSliderTest extends BaseTest {

    private HorizontalSliderPage horizontalSliderPage;

    @BeforeMethod
    public void initPage() {
        horizontalSliderPage = homePage.clickHorizontalSliderPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(horizontalSliderPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), horizontalSliderPage.getHeaderTitle()));
    }

    @Test(testName = "Slide to Number", priority = 2, dataProvider = "getSliderValue")
    public void testSlideToWholeNumber(JSONObject testData){
        horizontalSliderPage.setSliderValue(testData.getInt("slider_number"));
        assertEquals(horizontalSliderPage.getSliderValue(), testData.getString("slider_value"));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.horizontal-slider.header");
    }

    @DataProvider(name = "getSliderValue")
    public Object[][] getSliderValue() throws IOException {
        return getTestDataFromJSON("test-data.horizontal-slider.horizontal-slider");
    }
}
