package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTests extends TestBase {

    private static final String VALID_EMAIL = System.getenv("PINTEREST_EMAIL");
    private static final String VALID_PASSWORD = System.getenv("PINTEREST_PASSWORD");

    @Test(description = "Test 8: Click profile/menu, select logout, and verify login URL")
    public void testLogoutFlow() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.navigateToLoginPage();
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isLoggedIn = loginPage.isOnHomeFeed();
        Assert.assertTrue(isLoggedIn, "User should be logged in and on home feed");

        homePage.clickProfileMenu();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean menuDisplayed = homePage.isLogoutButtonVisible();
        Assert.assertTrue(menuDisplayed, "Profile menu with logout option should be visible");

        homePage.clickLogout();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean redirectedToLogin = homePage.isRedirectedToLogin();
        Assert.assertTrue(redirectedToLogin, "User should be redirected to login page after logout");

        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login") || currentUrl.contains("pinterest.com"),
                "URL should contain '/login' or be on Pinterest homepage after logout");
    }
}
