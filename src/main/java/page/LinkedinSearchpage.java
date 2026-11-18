package page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinSearchpage {

    WebDriver driver;
    WebDriverWait wait;

    // Search field locator (based on the HTML you shared)
    By searchField= By.cssSelector("input[data-testid='typeahead-input']");
    By postTextArea = By.cssSelector(
    	    "div[data-testid='ui-core-tiptap-text-editor-wrapper'] " +
    	    "div[contenteditable='true'][role='textbox']");

    public LinkedinSearchpage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

 // Verify search field
    public boolean verifySearchField() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(searchField)).isDisplayed();
    }

    // Enter keyword and search
    public void searchFor(String keyword) {

    	// Ensure post composer is closed
        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(postTextArea)
        );

        List<WebElement> fields = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(searchField)
        );

        WebElement search = null;

        for (WebElement field : fields) {

            if (field.isDisplayed() && field.isEnabled()) {
                search = field;
                break;
            }
        }

        if (search == null) {
            throw new RuntimeException("Search field is not available");
        }

        wait.until(ExpectedConditions.elementToBeClickable(search));

        search.click();
        search.sendKeys(Keys.CONTROL, "a");
        search.sendKeys(keyword);
        search.sendKeys(Keys.ENTER);

        System.out.println("Search performed: " + keyword);
    }

    // Verify search results page
    public boolean verifySearchResultsPage() {
        return wait.until(
            ExpectedConditions.urlContains("/search/results/")
        );
    }

    public String getSearchResultsUrl() {
        return driver.getCurrentUrl();
    }
}



