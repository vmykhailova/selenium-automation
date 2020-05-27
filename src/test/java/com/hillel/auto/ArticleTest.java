package com.hillel.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.Article;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.ArticleData;
import utils.UserData;


import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest extends TestBase {
    protected Article article =  ArticleData.createArticle();
//    protected User user =  UserData.defaultUser();
    protected HomePage homePage;
//    protected ArticleDetailsPage articleDetailsPage;



    @Test(priority = 1)
    public void createArticleTest() throws InterruptedException {
        homePage = new HomePage(driver);
        NewArticlePage newArticlePage = homePage.clickNewPost();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

        newArticlePage.inputArticleTitle(article.getTitle());
        newArticlePage.inputWhatArticleAbout(article.getDescription());
        newArticlePage.inputArticle(article.getText());
        newArticlePage.inputTags(article.getTag());

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.isPageOpen()).isTrue();
    }

    @Test(priority = 2)
    public void editArticleTest(){
        ProfilePage profilePage = homePage.clickProfile();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        WebElement article = driver.findElement(By.cssSelector(".article-preview h1"));
        article.click();

        NewArticlePage newArticlePage = homePage.clickEditPost();

        newArticlePage.inputArticleTitle("Edited Title");
        newArticlePage.inputWhatArticleAbout("Edited description");
        newArticlePage.inputArticle("Edited text");
        newArticlePage.inputTags("edited tag");

        ArticleDetailsPage articleDetailsPage = newArticlePage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.isPageOpen()).isTrue();

    }

    @Test(priority = 3)
    public void deleteArticleTest() throws InterruptedException {
        homePage.clickProfile();
        WebElement article = driver.findElement(By.cssSelector(".article-preview h1"));
        article.click();
        WebElement deleteBtn = driver.findElement(By.cssSelector("span button"));
        deleteBtn.click();
        Thread.sleep(1000);
        WebElement afterDeleteMessage = driver.findElement(By.cssSelector(".article-preview"));
        assertThat(afterDeleteMessage.getText()).isEqualTo("No articles are here... yet.");
    }

//    @Test
//    public void checkArticleSize() {
//        ProfilePage profilePage = homePage.clickProfile();
//        assertThat(profilePage.getArticlesSize()).isGreaterThan(2);
//    }
}