package pages.dynamicloading;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DynamicLoadingExample1Page;
import pages.DynamicLoadingExample2Page;
import pages.DynamicLoadingPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DynamicLoadingTest extends BaseTest {

    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Dynamic Loading");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        dynamicLoadingPage = homePage.clickDynamicLoadingPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dynamicLoadingPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Example Page 1", priority = 2, dataProvider = "getExampleOneMessageData", groups = {"regression"})
    public void testDynamicLoadingExampleOne(JSONObject testData) {
        DynamicLoadingExample1Page dynamicLoadingExample1PagePage = dynamicLoadingPage.exampleOne();
        dynamicLoadingExample1PagePage.clickStart();
        String actualMessage = dynamicLoadingExample1PagePage.getLoadedText();
        String expectedMessage = testData.getString("message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getExampleOneMessageData")
    private Object[][] getExampleOneMessageData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("example-one"));
    }

    @Test(testName = "Example Page 2", priority = 3, dataProvider = "getExampleTwoMessageData", groups = {"regression"})
    public void testDynamicLoadingExampleTwo(JSONObject testData) {
        DynamicLoadingExample2Page dynamicLoadingExample2PagePage = dynamicLoadingPage.exampleTwo();
        dynamicLoadingExample2PagePage.clickStart();
        String actualMessage = dynamicLoadingExample2PagePage.getLoadedText();
        String expectedMessage = testData.getString("message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getExampleTwoMessageData")
    private Object[][] getExampleTwoMessageData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("example-one"));
    }

}
