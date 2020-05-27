package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    private By profileBtn = By.xpath("//*[@class='user-pic']/..");
    public HomePage (WebDriver driver) {
        super(driver);
    }

    public boolean isUserLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        return userInfo.isDisplayed();
    }

    public NewArticlePage clickNewPost() {
//        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.findElement(By.cssSelector(".nav-link .ion-compose")).click();
        return new NewArticlePage(driver);
    }

    public NewArticlePage clickEditPost() {
        driver.findElement(By.cssSelector("span a")).click();
        return new NewArticlePage(driver);
    }

    public ProfilePage clickProfile() {
        driver.findElement(profileBtn).click();
        return new ProfilePage(driver);
    }

    public SettingsPage clickSettings() {
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.findElement(By.cssSelector("[href='#settings']")).click();
        return new SettingsPage(driver);
    }

}