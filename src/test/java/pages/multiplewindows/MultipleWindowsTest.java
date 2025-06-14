package pages.multiplewindows;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MultipleWindowsPage;
import pages.NewWindowPage;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class MultipleWindowsTest extends BaseTest {

    private MultipleWindowsPage multipleWindowsPage;

    @BeforeClass
    public void initTest() {
        test = extent.createTest("Multiple Windows");
    }

    @BeforeMethod
    public void initPage() {
        multipleWindowsPage = homePage.clickMultipleWindowsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = multipleWindowsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Open A New Window", priority = 2, dataProvider = "getOpenNewWindowData")
    public void testOpenANewWindow(JSONObject testData) {
        String oldWindowHandle = getWindowManager().getWindowHandle();
        Set<String> oldWindowHandles = getWindowManager().getWindowHandles();

        NewWindowPage newWindowPage = multipleWindowsPage.openANewWindow();

        getWindowManager().switchToNewTab(oldWindowHandles);
        assertEquals(newWindowPage.getHeaderTitle(), testData.getString("new_window_header"));
        driver.close();
        getWindowManager().switchToTab(oldWindowHandle);
        assertEquals(multipleWindowsPage.getHeaderTitle(), testData.getString("old_window_header"));
    }

    @Test(testName = "Open A New Window Via Right Click", priority = 3, dataProvider = "getOpenNewWindowData")
    public void testOpenANewWindowViaRightClick(JSONObject testData) {
        String oldWindowHandle = getWindowManager().getWindowHandle();
        Set<String> oldWindowHandles = getWindowManager().getWindowHandles();

        NewWindowPage newWindowPage = multipleWindowsPage.openANewWindowRightClick();

        getWindowManager().switchToNewTab(oldWindowHandles);
        assertEquals(newWindowPage.getHeaderTitle(), testData.getString("new_window_header"));
        driver.close();
        getWindowManager().switchToTab(oldWindowHandle);
        assertEquals(multipleWindowsPage.getHeaderTitle(), testData.getString("old_window_header"));
    }


    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.multiple-windows.header");
    }

    @DataProvider(name = "getOpenNewWindowData")
    private Object[][] getOpenNewWindowData() throws IOException {
        return getTestDataFromJSON("test-data.multiple-windows.new-window");
    }

}
