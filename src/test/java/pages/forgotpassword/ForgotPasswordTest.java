package pages.forgotpassword;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EmailSentPage;
import pages.ForgotPasswordPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void initPage() {
        forgotPasswordPage = homePage.clickForgotPasswordPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(forgotPasswordPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), forgotPasswordPage.getHeaderTitle()));
    }

    @Test(testName = "Retrieve Password", priority = 2, dataProvider = "getEmailDetails")
    public void testRetrievePassword(JSONObject testData) throws IOException {
        EmailSentPage emailSentPage = forgotPasswordPage.retrievePassword(testData.getString("email"));
        assertEquals(emailSentPage.getMessage(), testData.getString("message"),
                String.format("Expected: %s - Actual: %s", testData.getString("message"), emailSentPage.getMessage()));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.forgot-password.header");
    }

    @DataProvider(name = "getEmailDetails")
    public Object[][] getEmailDetails() throws IOException {
        return getTestDataFromJSON("test-data.forgot-password.forgot-password");
    }

}
