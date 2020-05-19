package com.hillel.auto;

import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.NewArticlePage;
import pageObjects.ProfilePage;
import pageObjects.SettingsPage;
import static org.testng.Assert.assertTrue;

public class UserNavigateTest extends TestBase{

    @Test
    public void navigateToSettingsTest() {
        SettingsPage settingsPage = homePage.clickSettings();
        assertTrue(settingsPage.isPageOpen());
    }

    @Test
    public void navigateToNewPostTest() {
        NewArticlePage newArticlePage = homePage.clickNewPost();
        assertTrue(newArticlePage.isPageOpen());
    }

    @Test
    public void navigateToUsername() {
        ProfilePage profilePage = homePage.clickProfile();
        assertTrue(profilePage.isPageOpen(user.getUserName()));
    }
}
