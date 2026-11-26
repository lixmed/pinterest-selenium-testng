package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class NavigationTests extends TestBase {

    @Test(description = "Test 6: Perform search, then use driver.navigate().back() to return to landing page")
    public void testNavigationBack() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.navigateAndExplore();

        String initialUrl = searchPage.getCurrentUrl();

        searchPage.search("Travel");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean onSearchPage = searchPage.isOnSearchResultsPage();
        Assert.assertTrue(onSearchPage, "Should be on search results page after search");

        String searchUrl = searchPage.getCurrentUrl();
        Assert.assertTrue(searchUrl.contains("search") || searchUrl.contains("Travel"),
                "URL should indicate search was performed");

        searchPage.navigateBack();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean backToLanding = searchPage.isOnLandingPage();
        Assert.assertTrue(backToLanding, "Should be back on landing page after navigating back");

        String currentUrl = searchPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/search/pins/"),
                "URL should not contain search path after navigating back");
    }
}
