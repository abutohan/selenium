package pages.formauthentication;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FormAuthenticationPage;
import pages.SecureAreaPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.ReadJSON.getTestDataFromJSON;

public class FormAuthenticationTest extends BaseTest {

    private FormAuthenticationPage formAuthenticationPage;

    @BeforeMethod
    public void initPage() {
        formAuthenticationPage = homePage.clickFormAuthenticationPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) throws IOException {
        assertEquals(formAuthenticationPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), formAuthenticationPage.getHeaderTitle()));
    }

    @Test(testName = "Login", priority = 1, dataProvider = "getUserCredentials")
    public void testLogin(JSONObject testData) throws IOException {
        formAuthenticationPage.setUsername(testData.getString("username"));
        formAuthenticationPage.setPassword(testData.getString("password"));
        SecureAreaPage secureAreaPage = formAuthenticationPage.clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains(testData.getString("message")),
                String.format("Expected: %s - Actual: %s", testData.getString("message"), secureAreaPage.getAlertText()));
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.form-authentication.header");
    }

    @DataProvider(name = "getUserCredentials")
    public Object[][] getUserCredentials() throws IOException {
        return getTestDataFromJSON("test-data.form-authentication.form-authentication");
    }

}
