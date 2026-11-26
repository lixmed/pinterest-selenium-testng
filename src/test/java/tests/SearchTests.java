package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTests extends TestBase {

    @Test(description = "Test 3: Search for 'Art' and verify the page title")
    public void testBasicSearch() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.navigateAndExplore();

        searchPage.search("Art");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean resultsDisplayed = searchPage.areSearchResultsDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed after searching for 'Art'");

        boolean titleContainsArt = searchPage.pageTitleContains("Art");
        Assert.assertTrue(titleContainsArt, "Page title should contain 'Art' after search");

        boolean isOnSearchPage = searchPage.isOnSearchResultsPage();
        Assert.assertTrue(isOnSearchPage, "URL should indicate search results page");
    }

    @Test(description = "Test 4: Search for 'Design' and scroll down 3000px using JavascriptExecutor")
    public void testInfiniteScroll() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.navigateAndExplore();

        searchPage.search("Design");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean resultsDisplayed = searchPage.areSearchResultsDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed after searching for 'Design'");

        searchPage.scrollDown(3000);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean stillOnSearchPage = searchPage.isOnSearchResultsPage();
        Assert.assertTrue(stillOnSearchPage, "Should remain on search results page after scrolling");

        String currentUrl = searchPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Design") || currentUrl.contains("design"),
                "URL should still contain 'Design' search term after scrolling");
    }

    @Test(description = "Test 7: Type in search bar and then clear it")
    public void testClearSearch() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.navigateAndExplore();

        String searchTerm = "Photography";
        searchPage.enterSearchQuery(searchTerm);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String valueBeforeClear = searchPage.getSearchBoxValue();
        Assert.assertTrue(valueBeforeClear.contains(searchTerm),
                "Search box should contain the entered text before clearing");

        searchPage.clearSearchBox();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String valueAfterClear = searchPage.getSearchBoxValue();
        Assert.assertTrue(valueAfterClear.isEmpty() || valueAfterClear.equals(""),
                "Search box should be empty after clearing");
    }
}
