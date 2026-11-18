package page;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinHomepage {
	
	WebDriver driver;
    WebDriverWait wait;
    
 // Navigation bar
    By navigationBar = By.xpath("//nav[.//button[starts-with(@aria-label,'Home')]]");

    // Search field
    By searchField = By.cssSelector("input[data-testid='typeahead-input']");

    // Feed post content
    By feedPosts = By.cssSelector("[data-testid='expandable-text-box']");

    // Start a post
    By startPostButton = By.cssSelector("div[aria-label='Start a post']");

    // Post editor - candidate locator
    By postTextArea = By.cssSelector("div[data-testid='ui-core-tiptap-text-editor-wrapper'] " +
    	    "div[contenteditable='true'][role='textbox']");
    
    By postButton=By.xpath("//button[.//span[normalize-space()='Post']]");
    
    By profileName = By.cssSelector("#meMenuV2WideComponentRef [aria-label$='Me']");
     
    // Constructor
    public LinkedinHomepage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // 1. Verify Home feed
    public boolean verifyHomeFeed() {

        try {
            wait.until(driver ->
                driver.getCurrentUrl().contains("/feed")
                || driver.getCurrentUrl().contains("/home")
            );

            System.out.println("Home feed URL: " + driver.getCurrentUrl());
            return true;

        } catch (TimeoutException e) {
            System.out.println("Home feed did not load.");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }


    // 2. Verify navigation bar
    public boolean verifyNavigationBar() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(navigationBar)).isDisplayed();
    }
 // Get profile name displayed on Home page
    public String getProfileName() {

        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(profileName));

        String name = profile.getAttribute("aria-label");

        // Remove "Me" from the accessible label
        name = name.replaceAll("\\s+Me$", "");

        return name.trim();
    }

    // 4. Verify feed posts
    public boolean verifyFeedPosts() {

        return wait.until(d -> {

            List<WebElement> posts = d.findElements(feedPosts);

            for (WebElement post : posts) {

                if (post.isDisplayed()) {
                    return true;
                }
            }

            return false;
        });
    }


    // Get visible feed post count
    public int getFeedPostCount() {

        return (int) driver.findElements(feedPosts).stream().filter(WebElement::isDisplayed).count();
    }

    // 5. Open post composer

    public void openPostComposer() {

    	wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("/feed"), ExpectedConditions.urlContains("/home")));

    	    WebElement button = wait.until( ExpectedConditions.elementToBeClickable(startPostButton));

    	    button.click();

    	    System.out.println("Post composer opened");
    	}
    
    public boolean verifyPostComposer() {

        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(postTextArea)
            ).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }
   
  //Enter and publish post
    
        public void publishPost(String postContent) {
    
            WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(postTextArea));
    
            textArea.click();
            textArea.sendKeys(postContent);
    
            wait.until(ExpectedConditions.elementToBeClickable(postButton)).click();
    
            System.out.println("Post published: " + postContent);
        }
        public void closePostComposer() {

            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);

            wait.until( ExpectedConditions.invisibilityOfElementLocated(postTextArea));

            System.out.println("Post composer closed");
        }
     //
        // Verify newly published post
    
        public WebElement findPost(String postContent) {

            By postLocator = By.xpath("//*[contains(normalize-space(.),'" + postContent + "')]");

            return wait.until( ExpectedConditions.visibilityOfElementLocated(postLocator) );
        }
    
        public boolean verifyPublishedPost(String postContent) {
    
            return findPost(postContent).isDisplayed();
        }
    
}


