package test;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.LinkedinBase;

public class LinkedinProfiletest extends LinkedinBase{
	
	
	@Test(priority = 16)
    public void openProfileMenuTest() {

        profile.openProfileMenu();

        Assert.assertTrue(profile.isProfileMenuOpened(),"Profile menu did not open");

        System.out.println("Profile menu opened successfully");
    }
	
	@Test(priority = 17)
	public void viewProfileTest() {

	    profile.clickViewProfile();
	
	    Assert.assertTrue(profile.verifyProfilePage(), "Profile page was not opened" );

	    System.out.println("Profile page opened successfully");

	    System.out.println("Profile URL: " + profile.getProfileUrl() );
	}
}
	
	


