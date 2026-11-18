package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.LinkedinBase;

public class LinkedinHometest extends LinkedinBase{
	
	// 1. Verify Home Feed
    @Test(priority = 7)
    public void verifyHomeFeedTest() {
       Assert.assertTrue(home.verifyHomeFeed(),"Home feed is not displayed");

        System.out.println("Home feed verification passed");
    }

    // 2. Verify Navigation Bar
    @Test(priority = 8)
    public void verifyNavigationBarTest() {

        Assert.assertTrue( home.verifyNavigationBar(),"Navigation bar is not displayed");

        System.out.println("Navigation bar verification passed");
    }
 // 3.Verify profile name displayed on Home page
    @Test(priority = 9)
    public void verifyProfileNameTest() {

   String name = home.getProfileName();
   Assert.assertFalse(name.isEmpty(),"Profile name is not displayed");

        System.out.println("Profile name: " + name);

        System.out.println("Profile name verification passed");
    }

    // 4. Verify Feed Posts
    @Test(priority = 10)
    public void verifyFeedPostsTest() {

        Assert.assertTrue( home.verifyFeedPosts(), "Feed posts are not displayed");

        int count = home.getFeedPostCount();

        System.out.println("Visible feed content count: " + count);
        System.out.println("Feed posts verification passed");
    }

    // 5. Verify Post Composer
    @Test(priority = 12)
    public void verifyPostComposerTest() {

        home.openPostComposer();

        Assert.assertTrue( home.verifyPostComposer(),"Post composer is not displayed");

        System.out.println("Post composer opened successfully");

        home.closePostComposer();

        System.out.println("Post composer closed successfully");
    }

    // 6. Publish a New Post
    @Test(priority = 13)
    public void publishPostTest() {

    	String postContent = "Selenium automation testing "+ System.currentTimeMillis();

        home.openPostComposer();

        Assert.assertTrue( home.verifyPostComposer(),"Post composer did not open");

        home.publishPost(postContent);
        
       
        Assert.assertTrue(home.verifyPublishedPost(postContent),"Published post was not found");

        System.out.println("Post publishing test passed");

        home.closePostComposer();

        System.out.println("Post composer closed successfully");
    }
    
   
}