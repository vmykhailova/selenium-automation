package com.hillel.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.Article;
import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.ArticleData;
import utils.UserData;


import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest extends TestBase {
    protected Article article =  ArticleData.createArticle();
    protected User user =  UserData.defaultUser();
    protected HomePage homePage;
    protected ArticleDetailsPage articleDetailsPage;
    protected WebDriver driver;
    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.get("https://react-redux.realworld.io/");
        clickLoginButton();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        login();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public void login() {

        LoginPage loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        homePage = loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isUserLoggedIn(user.getUserName())).isTrue();
    }

    @Test(priority = 1)
    public void createArticleTest() {

        NewArticlePage newArticlePage = homePage.clickNewPost();

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