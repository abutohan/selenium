package pages.keypresses;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.KeyPressesPage;

import java.io.IOException;
import java.util.Iterator;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class KeyPressesTest extends BaseTest {

    private KeyPressesPage keyPressesPage;

    @BeforeMethod
    public void initPage() {
        keyPressesPage = homePage.clickKeyPressesPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(keyPressesPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), keyPressesPage.getHeaderTitle()));
    }

    @Test(testName = "Key Presses", priority = 2, dataProvider = "getKeyPress")
    public void testKeyPresses(JSONObject testData) {
        Iterator<String> keys = testData.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String enumKeyPress = String.valueOf(testData.getEnum(Keys.class, key));
            keyPressesPage.enterText(enumKeyPress);
            assertEquals(keyPressesPage.getResult(), String.format("You entered: %s", testData.getString(key)), "Input result incorrect");
        }
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.key-presses.header");
    }

    @DataProvider(name = "getKeyPress")
    private Object[][] getKeyPress() throws IOException {
        return getTestDataFromJSON("test-data.key-presses.key-presses");
    }
}
