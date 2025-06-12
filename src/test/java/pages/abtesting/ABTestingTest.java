package pages.abtesting;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ABTestingPage;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.ReadJSON.getTestDataFromJSON;

public class ABTestingTest extends BaseTest {

    private ABTestingPage abTestingPage;

    @BeforeMethod
    public void initPage(){
        abTestingPage = homePage.clickABTestingPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testABTesting(JSONObject testData) {
        assertThat(abTestingPage.getHeaderTitle(), containsString(testData.getString("header_title")));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.ab-testing.header");
    }

}
