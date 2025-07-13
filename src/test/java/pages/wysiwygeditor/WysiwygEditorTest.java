package pages.wysiwygeditor;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.WysiwygEditorPage;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static utils.Messages.onFailure;
import static utils.ReadJSON.getTestDataFromJSON;

public class WysiwygEditorTest extends BaseTest {

    private WysiwygEditorPage wysiwygEditorPage;

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        test = extent.createTest("WYSIWYG Editor");
    }

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        wysiwygEditorPage = homePage.clickWysiwygEditorPage();
    }

    @Test(testName = "Page is Displayed", priority = 1, dataProvider = "getHeaderTitle", groups = {"smoke"})
    public void testHeaderTitle(JSONObject testData) {
        String actualHeaderTitle = wysiwygEditorPage.getHeaderTitle();
        String expectedHeaderTitle = testData.getString("header_title");
        assertEquals(actualHeaderTitle, expectedHeaderTitle,
                onFailure(expectedHeaderTitle, actualHeaderTitle));
    }

    @DataProvider(name = "getHeaderTitle")
    private Object[][] getHeaderTitle(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("header"));
    }

    @Test(testName = "WYSIWYG Editor", priority = 2, dataProvider = "getWysiwygEditorData", groups = {"regression"})
    public void testWysiwygEditor(JSONObject testData) {
        String expectedWysiwygEditorText = testData.getString("message");
        String actualWysiwygEditorText = wysiwygEditorPage.getTextFromEditor();
        assertEquals(actualWysiwygEditorText, expectedWysiwygEditorText,
                onFailure(expectedWysiwygEditorText, actualWysiwygEditorText));
    }

    @DataProvider(name = "getWysiwygEditorData")
    private Object[][] getWysiwygEditorData(ITestContext context) throws IOException {
        return getTestDataFromJSON(context.getCurrentXmlTest().getParameter("editor-text"));
    }
}
