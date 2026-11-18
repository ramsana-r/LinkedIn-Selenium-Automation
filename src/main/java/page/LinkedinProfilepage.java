package page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinProfilepage {
	
	
	WebDriver driver;
    WebDriverWait wait;
    public LinkedinProfilepage(WebDriver driver,WebDriverWait wait) 
    {
    	this.driver=driver;
		this.wait=wait;
	}

    // Profile menu
    By profileMenu = By.cssSelector("#meMenuV2WideComponentRef [role='button']");

        // View Profile link
        By viewProfileLink = By.cssSelector("a[href*='/in/sana-r-82345144b/']" );

        public void openProfileMenu() {
            wait.until(ExpectedConditions.elementToBeClickable(profileMenu)).click();
        }

        public boolean isProfileMenuOpened() {
            return "true".equals(
                wait.until(ExpectedConditions.visibilityOfElementLocated(profileMenu)).getAttribute("aria-expanded")
            );
        }

        // Click View Profile
        public void clickViewProfile() {

            wait.until( ExpectedConditions.elementToBeClickable(viewProfileLink)).click();

            wait.until(ExpectedConditions.urlContains("/in/"));
        }

        // Verify profile URL
        public boolean verifyProfilePage() {
            return driver.getCurrentUrl().contains("/in/sana-r-82345144b/");
        }

        // Get profile URL
        public String getProfileUrl() {
            return driver.getCurrentUrl();
        }

        
}




