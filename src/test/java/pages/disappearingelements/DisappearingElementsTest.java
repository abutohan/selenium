package pages.disappearingelements;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DisappearingElementsPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DisappearingElementsTest extends BaseTest {

    private DisappearingElementsPage disappearingElementsPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Disappearing Element");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        disappearingElementsPage = homePage.clickDisappearingElementsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = disappearingElementsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Verify Existence of Element", priority = 2, dataProvider = "getElementData", groups = {"regression"})
    public void testExistenceOfElement(JSONObject testData) {
        disappearingElementsPage.chekPresenceOfElementLink(testData.getString("element"));
    }

    @DataProvider(name = "getElementData")
    private Object[][] getElementData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("elements"));
    }

}
