package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

import java.util.List;

public class ProductsPage extends BaseTools {
    WebDriver driver;
    public String filePath1 = "src/test/java/utils/sunscreensData.txt";
    public String filePath2 = "src/test/java/utils/moisturizersData.txt";

    public List<WebElement> getPriceElements(String productTitle) {
        return findAll(driver,By.xpath("//p[contains(text(),'" + productTitle + "')]//following-sibling::p"));
    }

    public String getTitle(int price) {
        WebElement title = find(driver,By.xpath("//p[contains(text()," + price + ")]//preceding-sibling::p"));
        return getElementText(driver,title);
    }

    public WebElement getAddButton(int price) {
        return find(driver,By.xpath("//p[contains(text()," + price + ")]//following-sibling::button"));
    }

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeToTextFile(String key1, String key2, int index, int price, String filePath) {
        writeKeyValueToTextFile(key1 + index, getTitle(price), filePath);
        writeKeyValueToTextFile(key2 + index, String.valueOf(price), filePath);
    }

    public void clickOnAddButton(List<Integer> pricesList, int minPrice, int index) {
        for (Integer price : pricesList) {
            if (price == minPrice) {
                if (WelcomePage.product.equals("sunscreen")) {
                    writeToTextFile("sunscreenTitle", "sunscreenPrice", index, price, filePath1);
                } else {
                    writeToTextFile("moisturizerTitle", "moisturizerPrice", index, price, filePath2);
                }
                clickOnElement(driver,getAddButton(price), "Add button");
            }
        }
    }

    public int getTotalPrice(int price1, int price2) {
        return price1 + price2;
    }

    public void addProduct(String text1, String text2, String filePath) {
        List<Integer> pricesList1 = getPricesList(driver,getPriceElements(text1));
        List<Integer> pricesList2 = getPricesList(driver,getPriceElements(text2));
        int minPrice1 = getMinimumPrice(pricesList1);
        int minPrice2 = getMinimumPrice(pricesList2);
        String total = String.valueOf(getTotalPrice(minPrice1, minPrice2));
        clickOnAddButton(pricesList1, minPrice1, 1);
        clickOnAddButton(pricesList2, minPrice2, 2);
        writeKeyValueToTextFile("totalPrice", total, filePath);
    }

    public void addProductBasedOnType() {
        if (WelcomePage.product.equals("sunscreen")) {
            addProduct("SPF-50", "SPF-30", filePath1);
        } else {
            addProduct("Aloe", "Almond", filePath2);
        }
    }

}
