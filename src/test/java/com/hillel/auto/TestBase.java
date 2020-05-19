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
import pageObjects.ArticleDetailsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.ArticleData;
import utils.UserData;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class TestBase {
    public WebDriver driver;
    protected User user =  UserData.defaultUser();
    protected HomePage homePage;

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


    protected void checkPage(String pageHeader) {
        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo(pageHeader);
    }

    protected WebElement singForm() {
        return driver.findElement(By.cssSelector(".auth-page form"));
    }

    protected WebElement emailField() {
        return singForm().findElement(By.cssSelector("input[type='email']"));
    }

    protected WebElement passwordField() {
        return singForm().findElement(By.cssSelector("input[type='password']"));
    }

    protected void clickSingInButton() {
        WebElement signInButton = singForm().findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();
    }

    protected void inputText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void userShouldBeLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        assertThat(userInfo.isDisplayed()).isTrue();
    }

    protected void clickLoginButton() {

        WebElement signUpButton = driver.findElement(By.cssSelector("[href=\"#login\"]"));
        signUpButton.click();
    }


}
