package pages.horizontalslider;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Horizontal Slider");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        horizontalSliderPage = homePage.clickHorizontalSliderPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = horizontalSliderPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Slide to Number", priority = 2, dataProvider = "getSliderValueData", groups = {"regression"})
    public void testSlideToWholeNumber(JSONObject testData) {
        int sliderValue = testData.getInt("slider_number");
        horizontalSliderPage.setSliderValue(sliderValue);
        String actualValue = horizontalSliderPage.getSliderValue();
        String expectedValue = testData.getString("slider_value");
        assertEquals(actualValue, expectedValue,
                onFailure(expectedValue, actualValue));
    }

    @DataProvider(name = "getSliderValueData")
    private Object[][] getSliderValueData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("slider-value"));
    }

}
