package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends BasePage{

    private By articlePage = By.cssSelector(".article-page");

    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen() {
        return driver.findElement(articlePage).isDisplayed();
    }

    public void clickDeleteArticle(){
        driver.findElement(By.cssSelector("button .ion-trash-a")).click();
    }
}