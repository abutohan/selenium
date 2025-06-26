package pages.formauthentication;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FormAuthenticationPage;
import pages.SecureAreaPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class FormAuthenticationTest extends BaseTest {

    private FormAuthenticationPage formAuthenticationPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Form Authentication");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        formAuthenticationPage = homePage.clickFormAuthenticationPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = formAuthenticationPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Login", priority = 1, dataProvider = "getUserCredentialsDetails", groups = {"regression"})
    public void testLogin(JSONObject testData) {
        String username = testData.getString("username");
        String password = testData.getString("password");
        formAuthenticationPage.setUsername(username);
        formAuthenticationPage.setPassword(password);
        SecureAreaPage secureAreaPage = formAuthenticationPage.clickLoginButton();
        String actualAlertMessage = secureAreaPage.getAlertText();
        String expectedAlertMessage = testData.getString("message");
        assertTrue(actualAlertMessage.contains(expectedAlertMessage),
                onFailure(expectedAlertMessage, actualAlertMessage));
    }

    @DataProvider(name = "getUserCredentialsDetails")
    private Object[][] getUserCredentialsDetails(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("users"));
    }

}
