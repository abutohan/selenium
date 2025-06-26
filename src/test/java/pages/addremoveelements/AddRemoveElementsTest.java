package pages.addremoveelements;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;
import static utils.Screenshot.captureScreenshot;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;

    private final String testNameFolder = this.getClass().getSimpleName();

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Add/Remove Elements");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        addRemoveElementsPage = homePage.clickAddRemoveElementsPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = addRemoveElementsPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "Add/Remove Elements", priority = 2, dataProvider = "getAddRemoveElementsData", groups = {"regression"})
    public void testAddAndRemoveElements(JSONObject testData, Method method) throws IOException {
        int expectedNumbersOfElementsToBeAdded = testData.getInt("number_of_elements");
        addRemoveElementsPage.clickBtnAddElement(expectedNumbersOfElementsToBeAdded);
        int actualNumbersOfElementAdded = addRemoveElementsPage.getAddedElementsCount();

        String testName = Objects.requireNonNull(method.getAnnotation(Test.class)).testName();
        String screenshot = captureScreenshot(screenshotFolder, testNameFolder, method.getName(), addRemoveElementsPage.getDriver());
        test.log(Status.INFO, testName + " " + testData, MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());

        assertEquals(actualNumbersOfElementAdded, expectedNumbersOfElementsToBeAdded,
                onFailure(String.valueOf(expectedNumbersOfElementsToBeAdded), String.valueOf(actualNumbersOfElementAdded)));

        addRemoveElementsPage.clickBtnDeleteElement();

        actualNumbersOfElementAdded = addRemoveElementsPage.getAddedElementsCount();
        assertEquals(actualNumbersOfElementAdded, 0,
                onFailure(String.valueOf(0), String.valueOf(actualNumbersOfElementAdded)));
    }

    @DataProvider(name = "getAddRemoveElementsData")
    private Object[][] getAddRemoveElementsData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("add-remove-elements"));
    }

}
