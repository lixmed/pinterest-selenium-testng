package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class PinInteractionTests extends TestBase {

    @Test(description = "Test 5: Search for 'Anime' and use Actions class to hover over the first pin")
    public void testPinHover() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.navigateAndExplore();

        searchPage.search("Anime");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean resultsDisplayed = searchPage.areSearchResultsDisplayed();
        Assert.assertTrue(resultsDisplayed, "Search results should be displayed after searching for 'Anime'");

        boolean firstPinVisible = searchPage.isFirstPinDisplayed();
        Assert.assertTrue(firstPinVisible, "First pin should be visible in search results");

        searchPage.hoverOverFirstPin();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isOnSearchPage = searchPage.isOnSearchResultsPage();
        Assert.assertTrue(isOnSearchPage, "Should remain on search results page after hovering");

        String currentUrl = searchPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.toLowerCase().contains("anime"),
                "URL should contain 'anime' search term");
    }
}
