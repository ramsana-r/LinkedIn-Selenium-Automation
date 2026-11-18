package test;


import org.testng.Assert;
import org.testng.annotations.Test;

import base.LinkedinBase;

public class LinkedinLogintest extends LinkedinBase{
	
	@Test(priority=1)
    public void pageTitle() {
	
	// 1. Verify page title
    String title = login.getPageTitle();
    System.out.println("Page title: " + title);

    Assert.assertTrue(title.toLowerCase().contains("linkedin"),"LinkedIn page title is incorrect");
	}
    @Test(priority=2)
    public void linkedinLogoVerification()
    {
    	// 2. Verify LinkedIn logo
        Assert.assertTrue(login.logoVerification(),"LinkedIn logo is not displayed");
    }
    @Test(priority=3)
    public void welcomeHeading()
    {	
    // 3. Verify welcome heading
    Assert.assertTrue(login.verifyWelcomeHeading(),"Welcome heading is not displayed");
   
    }
    @Test(priority=4)
    public void signInwithemail()
    {
    	 // 4. Click Sign in with email
        login.clickSignInWithEmail();
    }
    @Test(priority=5)
    public void verifyLoginpage()
    {

        // 5. Verify email and password fields
        Assert.assertTrue(login.verifyLoginPage(),"Login page fields are not displayed");
    }
    @Test(priority=6)
    public void login()
    {
    	// 6. Enter credentials
        login.login("sanaramus610@gmail.com", "Amma123@");
    }
    
    
}
	




