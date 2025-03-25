package pages.addremoveelements;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;

    @BeforeMethod
    public void initPage() {
        addRemoveElementsPage = homePage.clickAddRemoveElementsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(addRemoveElementsPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), addRemoveElementsPage.getHeaderTitle()));
    }

    @Test(testName = "Add/Remove Elements", priority = 2, dataProvider = "addRemoveElements")
    public void testAddElement(JSONObject testData) {

        addRemoveElementsPage.clickBtnAddElement(testData.getInt("number_of_elements"));
        assertEquals(addRemoveElementsPage.getAddedElementsCount(), testData.getInt("number_of_elements"),
                String.format("Expected: %d - Actual: %d", testData.getInt("number_of_elements"), addRemoveElementsPage.getAddedElementsCount()));

        addRemoveElementsPage.clickBtnDeleteElement();
        assertEquals(addRemoveElementsPage.getAddedElementsCount(), 0,
                String.format("Expected: %d - Actual: %d", 0, addRemoveElementsPage.getAddedElementsCount()));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.add-remove-elements.header");
    }

    @DataProvider(name = "addRemoveElements")
    private Object[][] addRemoveElements() throws IOException {
        return getTestDataFromJSON("test-data.add-remove-elements.add-remove-elements");
    }

}
