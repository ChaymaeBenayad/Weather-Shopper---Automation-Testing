package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

public class CartPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//h2[text()='Checkout']")
    WebElement pageHeader;
    @FindBy(xpath = "//p[@id='total']")
    WebElement totalPrice;
    @FindBy(xpath = "//button/span[text()='Pay with Card']")
    WebElement payWithCardButton;
    @FindBy(xpath = "//h1[text()='Stripe.com']")
    WebElement formTitle;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@id='card_number']")
    WebElement cardNumberField;
    @FindBy(xpath = "//input[@id='cc-exp']")
    WebElement expirationDateField;
    @FindBy(xpath = "//input[@id='cc-csc']")
    WebElement cvcField;
    @FindBy(xpath = "//input[@id='billing-zip']")
    WebElement zipCodeField;

    @FindBy(xpath = "//button/descendant::span[contains(text(),'Pay')]")
    WebElement payButton;

    @FindBy(xpath = "//iframe[@name='stripe_checkout_app']")
    WebElement iframe;
    public String filePath1 = "src/test/java/utils/sunscreensData.txt";
    public String filePath2 = "src/test/java/utils/moisturizersData.txt";
    public String filePath = "src/test/java/utils/products.json";

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getTableRowsSize() {
        return findAll(driver, By.xpath("//table/tbody/tr")).size();
    }

    public String getTableElement(int i, int j) {
        WebElement tableElement = find(driver, By.xpath("//table/tbody/tr[" + i + "]/td[" + j + "]"));
        return getElementText(driver, tableElement);
    }

    public String getTotalPrice() {
        return getElementText(driver, totalPrice).replaceAll("Total: Rupees", "").trim();
    }

    public void verifyPageHeader() {
        // Assert.assertTrue(isElementVisible(pageHeader));
        Assertions.assertTrue(isElementVisible(pageHeader));
    }

    public void verifyCart(String key1, String key2, String filePath) {
        verifyPageHeader();
        for (int i = 1; i <= getTableRowsSize(); i++) {
            String expectedTitle = readValueFromTextFile(filePath, key1 + i);
            String expectedPrice = readValueFromTextFile(filePath, key2 + i);
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i, 1), expectedTitle));
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i, 2), expectedPrice));
        }
        String expectedTotal = readValueFromTextFile(filePath, "totalPrice");
        Assertions.assertTrue(isActualValueMatchingExpected(getTotalPrice(), expectedTotal));
    }

    public void verifyCartBasedOnType() {
        if (WelcomePage.product.equals("sunscreen")) {
            verifyCart("sunscreenTitle", "sunscreenPrice", filePath1);
        } else {
            verifyCart("moisturizerTitle", "moisturizerPrice", filePath2);
        }
    }

    public void verifySunscreensCart() {
        verifyPageHeader();
        for (int i = 1; i <= getTableRowsSize(); i++) {
            String expectedTitle = readValueFromTextFile(filePath1, "sunscreenTitle" + i);
            String expectedPrice = readValueFromTextFile(filePath1, "sunscreenPrice" + i);
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i, 1), expectedTitle));
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i, 2), expectedPrice));
        }
        String expectedTotal = readValueFromTextFile(filePath1, "totalPrice");
        Assertions.assertTrue(isActualValueMatchingExpected(getTotalPrice(), expectedTotal));
    }

    public void verifyMoisturizersCart() {
        verifyPageHeader();
        for (int i = 0; i < getTableRowsSize(); i++) {
            String expectedTitle = readJSONFileValue(filePath, i, "title");
            String expectedPrice = readJSONFileValue(filePath, i, "price");
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i + 1, 1), expectedTitle));
            Assertions.assertTrue(isActualValueMatchingExpected(getTableElement(i + 1, 2), expectedPrice));
        }
        String expectedTotal = String.valueOf(MoisturizersPage.totalPrice);
        Assertions.assertTrue(isActualValueMatchingExpected(getTotalPrice(), expectedTotal));
    }

    public void fillTheForm(String email, String cardNumber, String expirationDate, String cvc, String zipCode) {
        clickOnElement(driver, payWithCardButton, "Pay with Card button");
        switchToFrame(driver, iframe);
        typeText(driver, emailField, email);
        typeTextJs(driver, cardNumberField, cardNumber);
        typeTextJs(driver, expirationDateField, expirationDate);
        typeText(driver, cvcField, cvc);
        typeText(driver, zipCodeField, zipCode);
    }

    public void submitForm() {
        clickOnElement(driver, payButton, "Pay button");
    }

    public void verifyIamOnForm() {
        Assertions.assertTrue(isElementVisible(formTitle));
    }
}
