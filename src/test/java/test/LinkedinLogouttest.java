package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.LinkedinBase;

public class LinkedinLogouttest extends LinkedinBase{
	
	@Test(priority = 19)
	public void logoutTest() {

		    logout.clickSignOut();

		    Assert.assertTrue(
		        logout.verifyLogout(),
		        "Logout verification failed"
		    );

		    System.out.println("Logout test passed");
		}
	
}
