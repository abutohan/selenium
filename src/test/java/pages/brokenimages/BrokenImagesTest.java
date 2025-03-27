package pages.brokenimages;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BrokenImagesPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class BrokenImagesTest extends BaseTest {

    private BrokenImagesPage brokenImagesPage;

    @BeforeMethod
    public void initPage() {
        brokenImagesPage = homePage.clickBrokenImagesPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(brokenImagesPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), brokenImagesPage.getHeaderTitle()));
    }

    @Test(testName = "Count Broken Images", priority = 2, dataProvider = "countBrokenImages")
    public void testCountBrokenImages(JSONObject testData) {
        assertEquals(brokenImagesPage.countBrokenImages(), testData.getInt("number_of_broken_img"),
                String.format("Expected: %d - Actual: %d", testData.getInt("number_of_broken_img"), brokenImagesPage.countBrokenImages()));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.broken-images.header");
    }

    @DataProvider(name = "countBrokenImages")
    public Object[][] countBrokenImages() throws IOException {
        return getTestDataFromJSON("test-data.broken-images.broken-images");
    }
}
