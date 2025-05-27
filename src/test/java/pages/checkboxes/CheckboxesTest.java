package pages.checkboxes;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckboxesPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class CheckboxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeMethod
    public void initPage() {
        checkboxesPage = homePage.clickCheckboxesPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(checkboxesPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), checkboxesPage.getHeaderTitle()));
    }

    @Test(testName = "Tick Checkboxes", priority = 2, dataProvider = "tickCheckboxes")
    public void testTickCheckboxes(JSONObject testData) {

        //get possible count of checkbox
        int checkboxesCount = checkboxesPage.getCheckboxCount();
        //populate hash map with test data
        HashMap<Integer, Boolean> checkboxConfig = new HashMap<>();
        for (int i = 0; i < checkboxesCount; i++) {
            checkboxConfig.put(i, testData.getBoolean("checkbox_" + (i + 1)));
        }
        //tick checkboxes
        checkboxesPage.tickCheckboxes(checkboxConfig);
        //get checkboxes state
        List<WebElement> checkboxes = checkboxesPage.getCheckboxes();
        //get state box state
        for (int i = 0; i < checkboxesCount; i++) {
            assertEquals(checkboxes.get(i).isSelected(), testData.getBoolean("checkbox_" + (i + 1)),
                    String.format("Expected: %s - Actual: %s", checkboxes.get(i).isSelected(), testData.getBoolean("checkbox_" + (i + 1))));
        }
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.checkboxes.header");
    }

    @DataProvider(name = "tickCheckboxes")
    public Object[][] tickCheckboxes() throws IOException {
        return getTestDataFromJSON("test-data.checkboxes.checkboxes");
    }
}
