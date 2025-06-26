package pages.forgotpassword;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
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

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Forgot Password");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        forgotPasswordPage = homePage.clickForgotPasswordPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = forgotPasswordPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Retrieve Password", priority = 2, dataProvider = "getEmailData", groups = {"regression"})
    public void testRetrievePassword(JSONObject testData) {
        String email = testData.getString("email");
        EmailSentPage emailSentPage = forgotPasswordPage.retrievePassword(email);
        String actualMessage = emailSentPage.getMessage();
        String expectedMessage = testData.getString("message");
        assertEquals(actualMessage, expectedMessage,
                onFailure(expectedMessage, actualMessage));
    }

    @DataProvider(name = "getEmailData")
    private Object[][] getEmailData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("emails"));
    }

}
