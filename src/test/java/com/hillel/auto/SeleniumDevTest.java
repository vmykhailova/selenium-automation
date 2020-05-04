package com.hillel.auto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class SeleniumDevTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
       driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }

    @Test
    public void seleniumDevSiteShouldBeOpened(){

        driver.get("https://www.selenium.dev/");
        String title = driver.getTitle();
        System.out.println(title);

//        assertThat(driver.getTitle().
//                isEqualTo("SeleniumHQ Browser Automation"));
        assertEquals(title, "SeleniumHQ Browser Automation");

        WebDriver.Window window = driver.manage().window();
        window.maximize();

//        driver.quit();
    }

    @Test
    public void seleniumProjectShouldBeOpened(){

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://www.selenium.dev/projects/");
        navigate.back();
        navigate.forward();
        navigate.refresh();


        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title, "Selenium Projects");
    }

    @Test
    public void seleniumDownloadsShouldBeOpened(){

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://www.selenium.dev/downloads/");

        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title, "Downloads");
    }

    @Test
    public void seleniumDocumentationShouldBeOpened(){

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://www.selenium.dev/documentation/en//");

        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title, "The Selenium Browser Automation Project :: Documentation for Selenium");
    }

    @Test
    public void seleniumSupportShouldBeOpened(){

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://www.selenium.dev/support/");

        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title, "Selenium Support");
    }

    @Test
    public void seleniumBlogShouldBeOpened(){

        WebDriver.Navigation navigate = driver.navigate();
        navigate.to("https://www.selenium.dev/blog/");


        String title = driver.getTitle();
        System.out.println(title);

        assertEquals(title, "Selenium Blog");
    }
}
