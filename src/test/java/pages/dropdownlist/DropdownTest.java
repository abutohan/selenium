package pages.dropdownlist;

import base.BaseTest;
import org.json.JSONObject;
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

    @BeforeMethod
    public void initPage() {
        dropdownPage = homePage.clickDropdownListPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dropdownPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Select From Dropdown List", priority = 2, dataProvider = "getDropdownOptionData")
    public void testDropdownList(JSONObject testData) {
        String actualDropdownOption = dropdownPage.selectFromDropDown(testData.getString("dropdown"));
        String expectedDropdownOption = testData.getString("dropdown");
        assertEquals(actualDropdownOption, expectedDropdownOption,
                onFailure(expectedDropdownOption, actualDropdownOption));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.dropdown.header");
    }

    @DataProvider(name = "getDropdownOptionData")
    private Object[][] getDropdownOptionData() throws IOException {
        return getTestDataFromJSON("test-data.dropdown.options");
    }

}
