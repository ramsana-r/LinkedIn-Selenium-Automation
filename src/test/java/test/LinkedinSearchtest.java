package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.LinkedinBase;

public class LinkedinSearchtest extends LinkedinBase {

    @Test(priority=15)
    public void searchTest() {

        // Verify search field
        Assert.assertTrue(search.verifySearchField(),"Search field is not displayed");

        System.out.println("Search field displayed");

        // Search for a keyword
        String keyword = "Software Tester";

        search.searchFor(keyword);

        System.out.println("Search keyword entered: " + keyword);

        // Verify search results
        Assert.assertTrue(search.verifySearchResultsPage(),"Search results page was not displayed");

        System.out.println("Search results page opened successfully");

        System.out.println("Current URL: " + search.getSearchResultsUrl());
    }
}


