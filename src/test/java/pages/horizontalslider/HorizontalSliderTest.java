package pages.horizontalslider;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HorizontalSliderPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class HorizontalSliderTest extends BaseTest {

    private HorizontalSliderPage horizontalSliderPage;

    @BeforeMethod
    public void initPage() {
        horizontalSliderPage = homePage.clickHorizontalSliderPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = horizontalSliderPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Slide to Number", priority = 2, dataProvider = "getSliderValueData")
    public void testSlideToWholeNumber(JSONObject testData) {
        int sliderValue = testData.getInt("slider_number");
        horizontalSliderPage.setSliderValue(sliderValue);
        String actualValue = horizontalSliderPage.getSliderValue();
        String expectedValue = testData.getString("slider_value");
        assertEquals(actualValue, expectedValue,
                onFailure(expectedValue, actualValue));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.horizontal-slider.header");
    }

    @DataProvider(name = "getSliderValueData")
    private Object[][] getSliderValueData() throws IOException {
        return getTestDataFromJSON("test-data.horizontal-slider.slider-value");
    }

}
