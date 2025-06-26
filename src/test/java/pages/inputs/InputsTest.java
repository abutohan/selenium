package pages.inputs;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InputsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class InputsTest extends BaseTest {

    private InputsPage inputsPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Inputs");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        inputsPage = homePage.clickInputsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = inputsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Set Number", priority = 2, dataProvider = "getNumbersToBeEnteredData", groups = {"regression"})
    public void testSetNumber(JSONObject testData) {
        int expectedValue = testData.getInt("number");
        String expectedValueStr = String.valueOf(expectedValue);
        inputsPage.setInputBySendingKeys(expectedValueStr);
        String actualValue = inputsPage.getInput();
        assertEquals(actualValue, expectedValueStr,
                onFailure(expectedValueStr, actualValue));
        inputsPage.clearInput();
        inputsPage.setInputByArrowKeys(expectedValue);
        actualValue = inputsPage.getInput();
        assertEquals(actualValue, expectedValueStr,
                onFailure(expectedValueStr, actualValue));
    }

    @DataProvider(name = "getNumbersToBeEnteredData")
    private Object[][] getNumbersToBeEnteredData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("inputs"));
    }

}
