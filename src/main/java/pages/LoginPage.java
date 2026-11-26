package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

    private static final String LOGIN_URL = "https://www.pinterest.com/login/";

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class, 'error') or contains(@class, 'Error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[@data-test-id='header-avatar'] | //div[@data-test-id='user-avatar']")
    private WebElement userAvatar;

    @FindBy(xpath = "//span[contains(text(), 'password') or contains(text(), 'email') or contains(text(), 'wrong')]")
    private WebElement loginErrorText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get(LOGIN_URL);
    }

    public void enterEmail(String email) {
        waitForElementToBeVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        waitForElementToBeVisible(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(loginButton);
        loginButton.click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isUserLoggedIn() {
        try {
            wait.until(ExpectedConditions.visibilityOf(userAvatar));
            return userAvatar.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginErrorText));
            return loginErrorText.isDisplayed();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.visibilityOf(errorMessage));
                return errorMessage.isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean isOnHomeFeed() {
        try {
            wait.until(ExpectedConditions.urlContains("pinterest.com"));
            String currentUrl = getCurrentUrl();
            return !currentUrl.contains("/login") && 
                   (currentUrl.contains("pinterest.com") || currentUrl.endsWith("/"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("/login");
    }
}
