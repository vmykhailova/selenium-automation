package com.hillel.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class UserNavigateTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void navigateOfLoginUser() {
        String user = "at_username2";
        String email = "at_username2@te.com";
        String password = "1Qqqqwwwww!";

        driver.get("https://react-redux.realworld.io/#/login?_k=te2vci");

        WebElement singInHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singInHeader.getText()).isEqualTo("Sign In");

        WebElement singInForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singInForm.isDisplayed()).isTrue();

        WebElement emailField = singInForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singInForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singInForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +user+"']"));
        assertThat(userInfo.isDisplayed()).isTrue();

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://react-redux.realworld.io/#/editor");

        WebElement ArticleTitle = driver.findElement(By.cssSelector("input[placeholder = 'Article Title']"));
        assertEquals(ArticleTitle.getAttribute("placeholder"), "Article Title");

        navigate.to("https://react-redux.realworld.io/#/settings");

        WebElement header = driver.findElement(By.cssSelector("h1"));
        assertEquals(header.getText(), "Your Settings");

        navigate.to("https://react-redux.realworld.io/#/@at_username2");

        WebElement header2 = driver.findElement(By.cssSelector("h4"));
        assertEquals(header2.getText(), user);
    }
}
