package pages.checkboxes;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CheckboxesPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class CheckboxesTest extends BaseTest {

    private CheckboxesPage checkboxesPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Checkboxes");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        checkboxesPage = homePage.clickCheckboxesPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle",  groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = checkboxesPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Tick Checkboxes", priority = 2, dataProvider = "getCheckboxesData",  groups = {"regression"})
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
            boolean actualCheckboxStatus = checkboxes.get(i).isSelected();
            boolean expectedCheckboxStatus = testData.getBoolean("checkbox_" + (i + 1));
            assertEquals(actualCheckboxStatus, expectedCheckboxStatus,
                    onFailure(String.valueOf(expectedCheckboxStatus), String.valueOf(actualCheckboxStatus)));
        }
    }

    @DataProvider(name = "getCheckboxesData")
    private Object[][] getCheckboxesData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("checkboxes"));
    }

}
