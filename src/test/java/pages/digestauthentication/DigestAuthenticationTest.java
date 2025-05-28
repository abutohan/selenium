package pages.digestauthentication;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DigestAuthenticationPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DigestAuthenticationTest extends BaseTest {

    private DigestAuthenticationPage digestAuthenticationPage;

    @BeforeMethod
    public void initPage(){
        digestAuthenticationPage = homePage.clickDigestAuthPage();
    }

    @Test(testName = "Page Displayed Correctly", dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData){
        assertEquals(digestAuthenticationPage.getHeaderTitle(testData.getString("username"), testData.getString("password")), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"),
                        digestAuthenticationPage.getHeaderTitle(testData.getString("username"), testData.getString("password"))));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException{
        return getTestDataFromJSON("test-data.digest-authentication.header");
    }
}
