package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

public class ConfirmationPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//p[@class='text-justify']")
    WebElement confirmationMessage;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkConfirmationMessage(String expectedMessage) {
        switchToMainPage(driver);
        String actualMessage = getElementText(driver, confirmationMessage);
        Assertions.assertTrue(isActualValueMatchingExpected(actualMessage, expectedMessage));
    }
}
