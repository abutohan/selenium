package pages.keypresses;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import java.io.IOException;
import java.util.Iterator;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class KeyPressesTest extends BaseTest {

    private KeyPressesPage keyPressesPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Key Presses");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        keyPressesPage = homePage.clickKeyPressesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = keyPressesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Key Presses", priority = 2, dataProvider = "getKeyPress", groups = {"regression"})
    public void testKeyPresses(JSONObject testData) {
        Iterator<String> keys = testData.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String enumKeyPress = String.valueOf(testData.getEnum(Keys.class, key));
            keyPressesPage.enterText(enumKeyPress);
            String actualResult = keyPressesPage.getResult();
            String expectedResult = String.format("You entered: %s", testData.getString(key));
            assertEquals(actualResult, expectedResult,
                    onFailure(expectedResult, actualResult));
        }
    }

    @DataProvider(name = "getKeyPress")
    private Object[][] getKeyPress(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("key-presses"));
    }

}
