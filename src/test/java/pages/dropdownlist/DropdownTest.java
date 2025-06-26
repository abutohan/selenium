package pages.dropdownlist;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DropdownPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Dropdown");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        dropdownPage = homePage.clickDropdownListPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dropdownPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Select From Dropdown List", priority = 2, dataProvider = "getDropdownOptionData", groups = {"regression"})
    public void testDropdownList(JSONObject testData) {
        String actualDropdownOption = dropdownPage.selectFromDropDown(testData.getString("dropdown"));
        String expectedDropdownOption = testData.getString("dropdown");
        assertEquals(actualDropdownOption, expectedDropdownOption,
                onFailure(expectedDropdownOption, actualDropdownOption));
    }

    @DataProvider(name = "getDropdownOptionData")
    private Object[][] getDropdownOptionData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("options"));
    }

}
