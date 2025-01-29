package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoisturizersPage extends BaseTools {
    @FindBy(xpath = "//h2[text()='Moisturizers']")
    WebElement moisturizersTitle;
    @FindBy(xpath = "//button[contains(text(),'Cart')]")
    WebElement cartButton;
    public String filePath = "src/test/java/utils/products.json";
    public static int totalPrice;
    WebDriver driver;

    public List<WebElement> getPriceElements(String type) {
        return findAll(driver,By.xpath("//p[contains(text(),'" + type + "')]//following-sibling::p"));
    }

    public String getTitle(int price) {
        WebElement title = find(driver,By.xpath("//p[contains(text()," + price + ")]//preceding-sibling::p"));
        return getElementText(driver,title);
    }

    public WebElement getAddButton(int price) {
        return find(driver,By.xpath("//p[contains(text()," + price + ")]//following-sibling::button"));
    }

    public MoisturizersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyMoisturizersPage() {
        Assertions.assertTrue(isElementVisible(moisturizersTitle));
    }
    public void clickOnAddButton(List<Integer> minPricesList) {
        for (Integer minPrice : minPricesList) {
            HashMap<String, String> keyValuePairs = new HashMap<>();
            keyValuePairs.put("title",getTitle(minPrice));
            keyValuePairs.put("price",String.valueOf(minPrice));
            System.out.println(keyValuePairs);
            writeToJSONFile(keyValuePairs, filePath);
            clickOnElement(driver,getAddButton(minPrice), "Add button");
        }
        totalPrice=getTotalPrice(minPricesList);
    }
    public int getTotalPrice(List<Integer> prices) {
        int sum=0;
        for(int price:prices){
            sum+=price;
        }
        return sum;
    }

    public void addMoisturizers() {
        List<Integer> aloePricesList = getPricesList(driver,getPriceElements("Aloe"));
        List<Integer> almandPricesList = getPricesList(driver,getPriceElements("Almond"));
        List<Integer> minPricesList = new ArrayList<>();
        int aloeMinPrice = getMinimumPrice(aloePricesList);
        int almondMinPrice = getMinimumPrice(almandPricesList);
        minPricesList.add(aloeMinPrice);
        minPricesList.add(almondMinPrice);
        clickOnAddButton(minPricesList);
    }

    public void clickOnCart() {
        clickOnElement(driver,cartButton, "Cart button");
    }

}
