package pages.securefiledownload;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SecureFileDownloadPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class SecureFileDownloadTest extends BaseTest {

    private SecureFileDownloadPage secureFileDownloadPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Secure File Download");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage(){
        secureFileDownloadPage = homePage.clickSecureFileDownloadPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke", "regression"})
    public void testHeaderTitle(JSONObject testData) {
        String username = testData.getString("username");
        String password = testData.getString("password");
        String actualHeaderTitle = secureFileDownloadPage.getHeaderTitle(username, password);
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

}
