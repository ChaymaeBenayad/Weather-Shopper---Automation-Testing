package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseTools;

import java.util.List;

public class SunscreensPage extends BaseTools {
    WebDriver driver;
    @FindBy(xpath = "//h2[text()='Sunscreens']")
    WebElement sunscreensHeader;

    @FindBy(xpath = "//button[contains(text(),'Cart')]")
    WebElement cartButton;

    public String filePath = "src/test/java/utils/sunscreensData.txt";

    public List<WebElement> getPriceElements(String reference) {
        return findAll(driver,By.xpath("//p[contains(text(),'SPF-" + reference + "')]//following-sibling::p"));
    }

    public String getTitle(int price) {
        WebElement title = find(driver,By.xpath("//p[contains(text()," + price + ")]//preceding-sibling::p"));
        return getElementText(driver,title);
    }

    public WebElement getAddButton(int price) {
        return find(driver,By.xpath("//p[contains(text()," + price + ")]//following-sibling::button"));
    }

    public SunscreensPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifySunscreensPage() {
        Assertions.assertTrue(isElementVisible(sunscreensHeader));
    }

    public void clickOnAddButton(List<Integer> pricesList, int minPrice,int index) {
        System.out.println(pricesList);
        for (Integer price : pricesList) {
            if (price == minPrice) {
                writeKeyValueToTextFile("sunscreenTitle" + index, getTitle(price), filePath);
                writeKeyValueToTextFile("sunscreenPrice" + index, String.valueOf(price), filePath);
                clickOnElement(driver,getAddButton(price), "Add button");
            }
        }
    }

    public int getTotalPrice(int price1,int price2){
        return price1+price2;
    }

    public void addSunscreens() {
        List<Integer> SPF50PricesList = getPricesList(driver,getPriceElements("50"));
        List<Integer> SPF30PricesList = getPricesList(driver,getPriceElements("30"));
        int SPF50MinPrice = getMinimumPrice(SPF50PricesList);
        int SPF30MinPrice = getMinimumPrice(SPF30PricesList);
        String total=String.valueOf(getTotalPrice(SPF50MinPrice,SPF30MinPrice));
        clickOnAddButton(SPF50PricesList, SPF50MinPrice,1);
        clickOnAddButton(SPF30PricesList, SPF30MinPrice,2);
        writeKeyValueToTextFile("totalPrice",total,filePath);
    }

    public void clickOnCart() {
        clickOnElement(driver,cartButton, "Cart button");
    }
}
