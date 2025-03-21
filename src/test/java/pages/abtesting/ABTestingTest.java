package pages.abtesting;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ABTestingPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class ABTestingTest extends BaseTest {

    @Test(testName = "A/B Testing Page Displayed Correctly")
    public void testABTesting() {
        ABTestingPage abTestingPage = homePage.clickABTestingPage();
        assertThat(abTestingPage.getHeaderTitle(), containsString("A/B Test"));
    }
}
