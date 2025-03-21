package pages.addremoveelements;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddRemoveElementsPage;

import static org.testng.Assert.assertEquals;

public class AddRemoveElementsTest extends BaseTest {

    private AddRemoveElementsPage addRemoveElementsPage;

    @BeforeMethod
    public void initPage() {
        addRemoveElementsPage = homePage.clickAddRemoveElementsPage();
    }

    @Test(testName = "Page Displayed Correctly", priority = 1)
    public void testHeaderTitle() {
        assertEquals(addRemoveElementsPage.getHeaderTitle(), "Add/Remove Elements",
                String.format("Expected: %s - Actual: %s", "Add/Remove Elements", addRemoveElementsPage.getHeaderTitle()));
    }

    @Test(testName = "Add Elements", priority = 2)
    public void testAddElement() {
        addRemoveElementsPage.clickBtnAddElement(100);
        assertEquals(addRemoveElementsPage.getAddedElementsCount(), 100,
                String.format("Expected: %d - Actual: %d", 100, addRemoveElementsPage.getAddedElementsCount()));
        addRemoveElementsPage.clickBtnDeleteElement(0);
        assertEquals(addRemoveElementsPage.getAddedElementsCount(), 0,
                String.format("Expected: %d - Actual: %d", 0, addRemoveElementsPage.getAddedElementsCount()));
    }
}
