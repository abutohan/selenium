package pages.dropdownlist;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DropdownPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DropdownTest extends BaseTest {

    private DropdownPage dropdownPage;

    @BeforeMethod
    public void initPage(){
        dropdownPage = homePage.clickDropdownListPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData){
        assertEquals(dropdownPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), dropdownPage.getHeaderTitle()));
    }

    @Test(testName = "Select From Dropdown List", priority = 2, dataProvider = "getDropdownOption")
    public void testDropdownList(JSONObject testData){
        assertEquals(dropdownPage.selectFromDropDown(testData.getString("dropdown")), testData.getString("dropdown"),
                String.format("Expected: %s - Actual: %s", testData.getString("dropdown"),
                        dropdownPage.selectFromDropDown(testData.getString("dropdown"))));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.dropdown.header");
    }

    @DataProvider(name = "getDropdownOption")
    public Object[][] getDropdownOption() throws IOException {
        return getTestDataFromJSON("test-data.dropdown.dropdown");
    }
}
