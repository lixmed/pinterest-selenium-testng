package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends TestBase {

    private static final String VALID_EMAIL = System.getenv("PINTEREST_EMAIL");
    private static final String VALID_PASSWORD = System.getenv("PINTEREST_PASSWORD");
    private static final String INVALID_EMAIL = "invalid@test.com";
    private static final String INVALID_PASSWORD = "wrongpassword";


    @Test(priority = 1, description = "Test 1: Verify error message appears for invalid login credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.login(INVALID_EMAIL, INVALID_PASSWORD);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(errorDisplayed, "Error message should be displayed for invalid credentials");

        boolean stillOnLoginPage = loginPage.isOnLoginPage();
        Assert.assertTrue(stillOnLoginPage, "User should remain on login page after failed login attempt");
    }


    @Test(priority = 2, description = "Test 2: Verify successful login redirects to home feed")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean isOnHomeFeed = loginPage.isOnHomeFeed();
        Assert.assertTrue(isOnHomeFeed, "User should be redirected to home feed after successful login");

        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("/login"), "URL should not contain '/login' after successful login");
    }

    
}
