package page;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinLogoutpage {
	

	WebDriver driver;
    WebDriverWait wait;

    // Profile menu button
    By profileMenu = By.cssSelector("#meMenuV2WideComponentRef [role='button']" );

    // Sign out link
    By signOutButton = By.cssSelector( "a[href*='/m/logout/']");


    public LinkedinLogoutpage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Open profile menu
    public void openProfileMenu() {

    	WebElement profileMenu = wait.until(
    	        ExpectedConditions.elementToBeClickable(By.cssSelector("#meMenuV2WideComponentRef [role='button']") ));

    	    profileMenu.click();

    	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='menu']")));

    	    System.out.println("Profile menu opened");
    	}
    
 // Click Sign out
    public void clickSignOut() {

        // Check whether Sign out is already visible
        List<WebElement> signOutLinks =
                driver.findElements(signOutButton);

        boolean menuAlreadyOpen = false;

        for (WebElement element : signOutLinks) {
            if (element.isDisplayed()) {
                menuAlreadyOpen = true;
                break;
            }
        }

        // Open profile menu only if it is closed
        if (!menuAlreadyOpen) {

            WebElement menuButton = wait.until(
                ExpectedConditions.elementToBeClickable(profileMenu) );

            menuButton.click();

            System.out.println("Profile menu opened");
        }

        // Wait for Sign out link
        WebElement signOut = wait.until(
            ExpectedConditions.elementToBeClickable(signOutButton));

        System.out.println("Sign out link displayed");

        // Click Sign out
        signOut.click();

        System.out.println("Sign out clicked successfully");

        // Wait for login page
        wait.until(ExpectedConditions.urlContains("/login"));

        System.out.println("Logout successful");
    }
    
//Verify login page after logout
    public boolean verifyLogout() {

        wait.until(ExpectedConditions.urlContains("/login"));

        return driver.getCurrentUrl().contains("/login");
    }
}




