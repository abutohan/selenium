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
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordPage forgotPasswordPage;

    @BeforeMethod
    public void initPage() {
        forgotPasswordPage = homePage.clickForgotPasswordPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = forgotPasswordPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @Test(testName = "Retrieve Password", priority = 2, dataProvider = "getEmailData")
    public void testRetrievePassword(JSONObject testData) {
        String email = testData.getString("email");
        EmailSentPage emailSentPage = forgotPasswordPage.retrievePassword(email);
        String actualMessage = emailSentPage.getMessage();
        String expectedMessage = testData.getString("message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.forgot-password.header");
    }

    @DataProvider(name = "getEmailData")
    private Object[][] getEmailData() throws IOException {
        return getTestDataFromJSON("test-data.forgot-password.emails");
    }

}
