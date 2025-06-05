package pages.inputs;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InputsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class InputsTest extends BaseTest {

    private InputsPage inputsPage;

    @BeforeMethod
    public void initPage() {
        inputsPage = homePage.clickInputsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(inputsPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), inputsPage.getHeaderTitle()));
    }

    @Test(testName = "Set Number", priority = 2, dataProvider = "getNumbersToBeEntered")
    public void testSetNumber(JSONObject testData) {
        inputsPage.setInputBySendingKeys(String.valueOf(testData.getInt("number")));
        assertEquals(inputsPage.getInput(), String.valueOf(testData.getInt("number")));
        inputsPage.clearInput();
        inputsPage.setInputByArrowKeys(testData.getInt("number"));
        assertEquals(inputsPage.getInput(), String.valueOf(testData.getInt("number")));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.inputs.header");
    }

    @DataProvider(name = "getNumbersToBeEntered")
    private Object[][] getNumbersToBeEntered() throws IOException {
        return getTestDataFromJSON("test-data.inputs.inputs");
    }

}
