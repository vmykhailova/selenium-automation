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

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class UserLoginTest {

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
    public void loginTest() {
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
    }

        @Test
        public void blankEmailOrPasswordTest() {

            driver.get("https://react-redux.realworld.io/#/login?_k=te2vci");

            WebElement singInHeader = driver.findElement(By.cssSelector(".auth-page h1"));
            assertThat(singInHeader.getText()).isEqualTo("Sign In");

            WebElement singInForm = driver.findElement(By.cssSelector(".auth-page form"));
            assertThat(singInForm.isDisplayed()).isTrue();

            WebElement signInButton = singInForm.findElement(By.cssSelector("button[type='submit']"));
            signInButton.click();

            List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
            assertThat(errorsMessage.get(0).getText()).isEqualTo("email or password is invalid");
        }

        @Test
        public void wrongPasswordTest() {

            String email = "at_username2@te.com";
            String password = "yruururlm";

            driver.get("https://react-redux.realworld.io/#/login?_k=te2vci");

            WebElement singInHeader = driver.findElement(By.cssSelector(".auth-page h1"));
            assertThat(singInHeader.getText()).isEqualTo("Sign In");

            WebElement singInForm = driver.findElement(By.cssSelector(".auth-page form"));
            assertThat(singInForm.isDisplayed()).isTrue();

            WebElement signInButton = singInForm.findElement(By.cssSelector("button[type='submit']"));
            signInButton.click();

            List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
            assertThat(errorsMessage.get(0).getText()).isEqualTo("email or password is invalid");
        }
}
