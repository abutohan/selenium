package pages.abtesting;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ABTestingPage;

import static org.testng.Assert.assertEquals;

public class ABTestingTest extends BaseTest {

    @Test(testName = "A/B Testing Page Displayed Correctly")
    public void testABTesting(){
        ABTestingPage abTestingPage = homePage.clickABTestingPage();
        assertEquals(abTestingPage.getHeaderTitle(), "A/B Test Control",
                String.format("Expected: %s - Actual: %s", "A/B Test Control", abTestingPage.getHeaderTitle()));
    }
}
