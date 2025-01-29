package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

public class WelcomePage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//span[@id='temperature']")
    WebElement currentTemperature;

    @FindBy(xpath = "//h2[text()='Current temperature']")
    WebElement header;
    public static String product;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyPageHeader() {
        Assertions.assertTrue(isElementVisible(header));
    }

    public String getCurrentTemperature() {
        return currentTemperature.getText().substring(0, 2).trim();
    }

    public WebElement getButton(String buttonText) {
        By buyButton = By.xpath("//button[text()='" + buttonText + "']");
        return find(driver,buyButton);
    }

    public void clickOnButton(String buttonText) {
        clickOnElement(driver,getButton(buttonText), buttonText + " button");
    }

    public void checkCurrentTemperature() {
        int currentTemperature = Integer.parseInt(getCurrentTemperature());
        if (currentTemperature < 19) {
            product="moisturizer";
            clickOnButton("Buy moisturizers");
        } else if (currentTemperature > 34) {
            clickOnButton("Buy sunscreens");
            product="sunscreen";
        }
    }

}
