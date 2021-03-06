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

/**
 * Created by alpa on 5/3/20
 */
public class UserRegistrationTest extends TestBase{

    @Test
    public void registrationTest() {
        driver.get("https://react-redux.realworld.io/");

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "at_username" + new Random().nextInt(10000);
        String email = userName + "@te.com";
        String password = "1Qqqqwwwww!";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        assertThat(userInfo.isDisplayed()).isTrue();
    }

    @Test
    public void invalidEmailTest(){

        driver.get("https://react-redux.realworld.io/");

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "at_username" + new Random().nextInt(10000);
        String email = userName + "@tecom";
        String password = "1Qqqqwwwww!";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
        assertThat(errorsMessage.get(0).getText()).isEqualTo("email is invalid");
    }

    @Test
    public void tooShortPasswordTest(){

        driver.get("https://react-redux.realworld.io/");

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "at_username" + new Random().nextInt(10000);
        String email = userName + "@te.com";
        String password = "1Qqq";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
        assertThat(errorsMessage.get(0).getText()).isEqualTo("password is too short (minimum is 8 characters)");
    }

    @Test
    public void emailAlreadyExistTest(){

        driver.get("https://react-redux.realworld.io/");

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "at_username";
        String email = userName + "@te.com";
        String password = "1Qqqqwwww!";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
        assertThat(errorsMessage.get(0).getText()).isEqualTo("email has already been taken");
    }

    @Test
    public void blankUserNameTest(){

        driver.get("https://react-redux.realworld.io/");

        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();

        WebElement singUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
        assertThat(singUpHeader.getText()).isEqualTo("Sign Up");

        WebElement singUpForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(singUpForm.isDisplayed()).isTrue();

        String userName = "";
        String email = "username8354@te.com";
        String password = "1Qqqwwww!";

        WebElement userNameField = singUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(userName);

        WebElement emailField = singUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = singUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = singUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        List<WebElement> errorsMessage = driver.findElements(By.cssSelector(".error-messages>li"));
        assertThat(errorsMessage.get(0).getText()).isEqualTo("username can't be blankis too short (minimum is 1 character)is too long (maximum is 20 characters)");
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

    private void clickRegistrationButton() {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();
    }

    private WebElement userNameField() {
        return singForm().findElement(By.cssSelector("input[type='text']"));
    }
}
