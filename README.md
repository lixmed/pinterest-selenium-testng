# Pinterest Automation Project

Selenium automation project for Pinterest using Java, TestNG, and Page Object Model (POM).
Designed for a 6-person team with 8 test scripts.

## Project Structure

```
pinterest-automation/
├── pom.xml                                    # Maven dependencies
├── testng.xml                                 # TestNG suite (parallel execution)
├── src/
│   ├── main/java/pages/
│   │   ├── PageBase.java                      # Base class with PageFactory, WebDriverWait, JS, Actions
│   │   ├── LoginPage.java                     # Login page object
│   │   ├── SearchPage.java                    # Search page object
│   │   └── HomePage.java                      # Home/profile page object
│   └── test/java/tests/
│       ├── TestBase.java                      # @BeforeMethod / @AfterMethod setup
│       ├── LoginTests.java                    # Tests 1-2: Login scenarios
│       ├── SearchTests.java                   # Tests 3-4, 7: Search scenarios
│       ├── PinInteractionTests.java           # Test 5: Hover interaction
│       ├── NavigationTests.java               # Test 6: Browser navigation
│       └── LogoutTests.java                   # Test 8: Logout flow
```

## The 8 Test Scripts

| # | Test Method | Description | Test Class |
|---|-------------|-------------|------------|
| 1 | `testSuccessfulLogin` | Verify redirect to home feed | LoginTests |
| 2 | `testInvalidLogin` | Verify error message appearance | LoginTests |
| 3 | `testBasicSearch` | Search for 'Art' and verify page title | SearchTests |
| 4 | `testInfiniteScroll` | Search 'Design' and scroll 3000px (JavascriptExecutor) | SearchTests |
| 5 | `testPinHover` | Search 'Anime' and hover first pin (Actions class) | PinInteractionTests |
| 6 | `testNavigationBack` | Search then navigate().back() | NavigationTests |
| 7 | `testClearSearch` | Type in search and clear it | SearchTests |
| 8 | `testLogoutFlow` | Click profile menu, logout, verify login URL | LogoutTests |

## Technologies & Patterns

| Requirement | Implementation |
|-------------|----------------|
| **PageFactory + @FindBy** | All page objects use declarative locators |
| **PageBase** | Constructor with PageFactory.initElements(), WebDriverWait, JavascriptExecutor, Actions |
| **TestBase** | @BeforeMethod (ChromeDriver setup), @AfterMethod (driver.quit()) |
| **WebDriverWait + ExpectedConditions** | Explicit waits throughout |
| **JavascriptExecutor** | scrollByPixels() for infinite scroll |
| **Actions class** | hoverOverElement() for mouse hover |
| **TestNG Assert** | All assertions use Assert.assertTrue/assertFalse |

## Prerequisites

1. Java JDK 11+
2. Maven 3.6+
3. Chrome browser

## Running Tests

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=LoginTests
mvn test -Dtest=SearchTests
mvn test -Dtest=PinInteractionTests

# Run specific test method
mvn test -Dtest=SearchTests#testInfiniteScroll

# Run with TestNG XML (parallel execution)
mvn test -DsuiteXmlFile=testng.xml
```

## Team Distribution

The testng.xml is configured for parallel execution with 6 threads:

- **Team Member 1-2**: LoginTests (testSuccessfulLogin, testInvalidLogin)
- **Team Member 3**: SearchTests - testBasicSearch
- **Team Member 4**: SearchTests - testInfiniteScroll, testClearSearch
- **Team Member 5**: PinInteractionTests, NavigationTests
- **Team Member 6**: LogoutTests

## Configuration

The login and logout tests read the test account from environment variables, so no credentials live in the code:

```powershell
$env:PINTEREST_EMAIL = "your-test-account@example.com"
$env:PINTEREST_PASSWORD = "your-test-password"
mvn test
```
