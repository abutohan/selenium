package pages.draganddrop;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class DragAndDropTest extends BaseTest {

    private DragAndDropPage dragAndDropPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("Drag and Drop");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        dragAndDropPage = homePage.clickDragAndDropPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = dragAndDropPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }


    @Test(testName = "Drag and Drop Element", priority = 2, dataProvider = "getBoxHeaderData", groups = {"regression"})
    public void testDragAndDropElement(JSONObject testData) {
        dragAndDropPage.dragAndDrop(testData.getString("scenario"));

        List<WebElement> boxes = dragAndDropPage.getBoxHeader();
        for (int i = 0; i < boxes.size(); i++) {
            String actualBoxHeader = boxes.get(i).getText();
            String expectedBoxHeader = testData.getString("box_element_" + (i + 1));
            assertEquals(actualBoxHeader, expectedBoxHeader,
                    onFailure(expectedBoxHeader, actualBoxHeader));
        }
    }

    @DataProvider(name = "getBoxHeaderData")
    private Object[][] getBoxHeaderData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("box-header"));
    }
}
