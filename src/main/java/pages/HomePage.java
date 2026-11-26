package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageBase {

    private static final String HOME_URL = "https://www.pinterest.com/";

    @FindBy(xpath = "//div[@data-test-id='header-avatar'] | //div[@data-test-id='user-avatar'] | //button[@data-test-id='header-accounts-options-button']")
    private WebElement profileMenuButton;

    @FindBy(xpath = "//div[contains(text(), 'Log out')] | //span[contains(text(), 'Log out')] | //button[contains(text(), 'Log out')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@data-test-id='header-menu'] | //div[@role='menu']")
    private WebElement profileMenu;

    @FindBy(xpath = "//button[@aria-label='Settings'] | //div[@data-test-id='settings-dropdown']")
    private WebElement settingsButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get(HOME_URL);
    }

    public void clickProfileMenu() {
        waitForElementToBeClickable(profileMenuButton);
        profileMenuButton.click();
    }

    public void clickLogout() {
        waitForElementToBeClickable(logoutButton);
        logoutButton.click();
    }

    public void performLogout() {
        clickProfileMenu();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickLogout();
    }

    public boolean isProfileMenuDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profileMenu));
            return profileMenu.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutButton));
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRedirectedToLogin() {
        try {
            wait.until(ExpectedConditions.urlContains("/login"));
            return getCurrentUrl().contains("/login");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnHomeFeed() {
        String currentUrl = getCurrentUrl();
        return currentUrl.contains("pinterest.com") && !currentUrl.contains("/login");
    }
}
