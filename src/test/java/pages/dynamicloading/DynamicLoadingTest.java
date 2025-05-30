package pages.dynamicloading;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DynamicLoadingExample1Page;
import pages.DynamicLoadingExample2Page;
import pages.DynamicLoadingPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DynamicLoadingTest extends BaseTest {

    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeMethod
    public void initPage() {
        dynamicLoadingPage = homePage.clickDynamicLoadingPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(dynamicLoadingPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), dynamicLoadingPage.getHeaderTitle()));
    }

    @Test(testName = "Example Page 1", priority = 2, dataProvider = "getExampleOneMessage")
    public void testDynamicLoadingExampleOne(JSONObject testData) {
        DynamicLoadingExample1Page dynamicLoadingExample1PagePage = dynamicLoadingPage.exampleOne();
        dynamicLoadingExample1PagePage.clickStart();
        assertEquals(dynamicLoadingExample1PagePage.getLoadedText(), testData.getString("message"),
                String.format("Expected: %s - Actual: %s", testData.getString("message"), dynamicLoadingExample1PagePage.getLoadedText()));
    }

    @Test(testName = "Example Page 2", priority = 3, dataProvider = "getExampleTwoMessage")
    public void testDynamicLoadingExampleTwo(JSONObject testData) {
        DynamicLoadingExample2Page dynamicLoadingExample2PagePage = dynamicLoadingPage.exampleTwo();
        dynamicLoadingExample2PagePage.clickStart();
        assertEquals(dynamicLoadingExample2PagePage.getLoadedText(), testData.getString("message"),
                String.format("Expected: %s - Actual: %s", testData.getString("message"), dynamicLoadingExample2PagePage.getLoadedText()));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.dynamic-loading.header");
    }

    @DataProvider(name = "getExampleOneMessage")
    public Object[][] getExampleOneMessage() throws IOException {
        return getTestDataFromJSON("test-data.dynamic-loading.dynamic-loading-example-one");
    }

    @DataProvider(name = "getExampleTwoMessage")
    public Object[][] getExampleTwoMessage() throws IOException {
        return getTestDataFromJSON("test-data.dynamic-loading.dynamic-loading-example-two");
    }

}
