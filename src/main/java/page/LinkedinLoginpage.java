package page;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinLoginpage {
	
	WebDriver driver;
	WebDriverWait wait;

	public LinkedinLoginpage(WebDriver driver,WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
		By linkedinlogo=By.cssSelector("svg[viewBox='0 0 84 21']");
		
		By welcomeHeading = By.xpath("//h1[contains(normalize-space(.),'Welcome to your professional community')]");
		
		By signinwithEmailbutton=By.xpath("//*[@id=\"main-content\"]/section[1]/div/div/a");

		By emailField = By.cssSelector( "input[type='email'][autocomplete='username webauthn']");

		By passwordField = By.cssSelector( "input[type='password'][autocomplete='current-password']");

		By signinButton = By.xpath("//button[@type='button']//span[normalize-space()='Sign in']/ancestor::button");
		
		// Get page title
	    public String getPageTitle() {
	        return driver.getTitle();
	    }

	    // Verify logo
	    public boolean logoVerification() {
	        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(linkedinlogo));
	        return logo.isDisplayed();
	    }

	    // Verify welcome heading
	    public boolean verifyWelcomeHeading() {
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeading));
	            return true;
	        } catch (TimeoutException e) {
	            System.out.println("Welcome heading not found.");
	            return false;
	        }
	    }
	    
	    // Click Sign in with email
	    public void clickSignInWithEmail() {
	        wait.until( ExpectedConditions.elementToBeClickable(signinwithEmailbutton)).click();
	    }

	    public boolean verifyLoginPage() {

	        java.util.List<WebElement> email =
	            driver.findElements(By.cssSelector("input[type='email']"));

	        java.util.List<WebElement> password =
	            driver.findElements(By.cssSelector("input[type='password']"));

	        System.out.println("Current URL: " + driver.getCurrentUrl());
	        System.out.println("Email fields found: " + email.size());
	        System.out.println("Password fields found: " + password.size());

	        return !email.isEmpty() && !password.isEmpty();
	    }

	    // Login

    public void login(String Email, String Password) {

    // Locate the visible email field
    WebElement email = wait.until(driver -> {
        for (WebElement element : driver.findElements(By.cssSelector("input[type='email']"))) {

            if (element.isDisplayed() && element.isEnabled()) {
                return element;
            }
        }
        return null;
    });

    email.clear();
    email.sendKeys(Email);

    // Verify email was entered
    String enteredEmail = email.getDomProperty("value");

    if (enteredEmail == null || enteredEmail.isEmpty()) {
        throw new RuntimeException("Email was not entered");
    }

    System.out.println("Email entered successfully");

    // Locate the visible password field
    WebElement password = wait.until(driver -> {
        for (WebElement element : driver.findElements(
                By.cssSelector("input[type='password']"))) {

            if (element.isDisplayed() && element.isEnabled()) {
                return element;
            }
        }
        return null;
    });

    password.clear();
    password.sendKeys(Password);

    // Verify password was entered without printing it
    String enteredPassword = password.getDomProperty("value");

    if (enteredPassword == null || enteredPassword.isEmpty()) {
        throw new RuntimeException("Password was not entered");
    }

    System.out.println("Password entered successfully");

    clickSignInButton();
}
public void clickSignInButton() {

    wait.until(driver -> {

        java.util.List<WebElement> buttons =
            driver.findElements(signinButton);

        for (WebElement button : buttons) {

            if (button.isDisplayed() && button.isEnabled()) {

                ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                    );

                button.click();
                return true;
            }
        }

        return false;
    });

    System.out.println("Sign in button clicked successfully");
}
}


	

	


