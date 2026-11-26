package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends PageBase {

    private static final String HOME_URL = "https://www.pinterest.com/";

    @FindBy(xpath = "//input[@name='searchBoxInput' or @data-test-id='search-box-input']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@aria-label='Search' or @type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@data-test-id='search-feed'] | //div[@data-test-id='pinGrid']")
    private WebElement searchResultsFeed;

    @FindBy(xpath = "(//div[@data-test-id='pin'] | //div[@data-test-id='pinWrapper'])[1]")
    private WebElement firstPinResult;

    @FindBy(xpath = "//div[@data-test-id='save-button'] | //button[contains(@aria-label, 'Save')]")
    private WebElement saveButtonOnHover;

    @FindBy(xpath = "//a[contains(text(), 'Explore')] | //span[contains(text(), 'Explore')]/parent::a | //div[contains(text(), 'Explore')]/parent::a | //a[@href='/ideas/']")
    private WebElement exploreButton;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get(HOME_URL);
    }

    public void clickExploreButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(exploreButton));
            exploreButton.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            // Explore button may not be present if already logged in or on explore page
        }
    }

    public void navigateAndExplore() {
        driver.get(HOME_URL);
        clickExploreButton();
    }

    public void enterSearchQuery(String query) {
        waitForElementToBeVisible(searchBox);
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public void clickSearchButton() {
        waitForElementToBeClickable(searchButton);
        searchButton.click();
    }

    public void submitSearch() {
        searchBox.sendKeys(Keys.ENTER);
    }

    public void search(String query) {
        enterSearchQuery(query);
        submitSearch();
    }

    public void clearSearchBox() {
        waitForElementToBeVisible(searchBox);
        searchBox.clear();
    }

    public void clearSearchBoxUsingKeys() {
        waitForElementToBeVisible(searchBox);
        searchBox.sendKeys(Keys.CONTROL + "a");
        searchBox.sendKeys(Keys.DELETE);
    }

    public String getSearchBoxValue() {
        waitForElementToBeVisible(searchBox);
        return searchBox.getAttribute("value");
    }

    public void scrollDown(int pixels) {
        scrollByPixels(pixels);
    }

    public void hoverOverFirstPin() {
        waitForElementToBeVisible(firstPinResult);
        hoverOverElement(firstPinResult);
    }

    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResultsFeed));
            return searchResultsFeed.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFirstPinDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(firstPinResult));
            return firstPinResult.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSaveButtonVisibleOnHover() {
        try {
            wait.until(ExpectedConditions.visibilityOf(saveButtonOnHover));
            return saveButtonOnHover.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnSearchResultsPage() {
        return getCurrentUrl().contains("/search/");
    }

    public boolean isOnLandingPage() {
        String currentUrl = getCurrentUrl();
        return currentUrl.equals(HOME_URL) || 
               currentUrl.equals("https://www.pinterest.com") ||
               (!currentUrl.contains("/search/") && currentUrl.contains("pinterest.com"));
    }

    public boolean pageTitleContains(String text) {
        return getPageTitle().toLowerCase().contains(text.toLowerCase());
    }
}
