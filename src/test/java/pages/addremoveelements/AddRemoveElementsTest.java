package pages.addremoveelements;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;

    @BeforeMethod
    public void initPage() {
        addRemoveElementsPage = homePage.clickAddRemoveElementsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = addRemoveElementsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Add/Remove Elements", priority = 2, dataProvider = "getAddRemoveElementsData")
    public void testAddAndRemoveElements(JSONObject testData) {
        int expectedNumbersOfElementsToBeAdded = testData.getInt("number_of_elements");
        addRemoveElementsPage.clickBtnAddElement(expectedNumbersOfElementsToBeAdded);
        int actualNumbersOfElementAdded = addRemoveElementsPage.getAddedElementsCount();
        assertEquals(actualNumbersOfElementAdded, expectedNumbersOfElementsToBeAdded,
                onFailure(String.valueOf(expectedNumbersOfElementsToBeAdded), String.valueOf(actualNumbersOfElementAdded)));

        addRemoveElementsPage.clickBtnDeleteElement();
        actualNumbersOfElementAdded = addRemoveElementsPage.getAddedElementsCount();
        assertEquals(actualNumbersOfElementAdded, 0,
                onFailure(String.valueOf(0), String.valueOf(actualNumbersOfElementAdded)));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.add-remove-elements.header");
    }

    @DataProvider(name = "getAddRemoveElementsData")
    private Object[][] getAddRemoveElementsData() throws IOException {
        return getTestDataFromJSON("test-data.add-remove-elements.add-remove-elements");
    }

}
