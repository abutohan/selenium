package pages.draganddrop;

import base.BaseTest;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DragAndDropPage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static utils.ReadJSON.getTestDataFromJSON;

public class DragAndDropTest extends BaseTest {

    private DragAndDropPage dragAndDropPage;

    @BeforeMethod
    public void initPage() {
        dragAndDropPage = homePage.clickDragAndDropPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1, dataProvider = "getHeaderTitle")
    public void testHeaderTitle(JSONObject testData) {
        assertEquals(dragAndDropPage.getHeaderTitle(), testData.getString("header_title"),
                String.format("Expected: %s - Actual: %s", testData.getString("header_title"), dragAndDropPage.getHeaderTitle()));
    }

    @Test(testName = "Drag and Drop Element", priority = 2, dataProvider = "getBoxHeader")
    public void testDragAndDropElement(JSONObject testData) {
        dragAndDropPage.dragAndDrop(testData.getString("scenario"));

        List<WebElement> boxes = dragAndDropPage.getBoxHeader();
        for (int i = 0; i < boxes.size(); i++) {
            assertEquals(boxes.get(i).getText(), testData.getString("box_element_" + (i + 1)));
        }
    }

    @DataProvider(name = "getHeaderTitle")
    public Object[][] getHeaderTitle() throws IOException {
        return getTestDataFromJSON("test-data.drag-and-drop.header");
    }

    @DataProvider(name = "getBoxHeader")
    public Object[][] getBoxHeader() throws IOException {
        return getTestDataFromJSON("test-data.drag-and-drop.drag-and-drop");
    }
}
