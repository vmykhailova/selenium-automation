package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SettingsPage extends BasePage {

    private By header = By.cssSelector("h1");

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen() {
        if (driver.findElement(header).getText().equals("Your Settings")) {
            return true;
        }
        return false;
    }

}
