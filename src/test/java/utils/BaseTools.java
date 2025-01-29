package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BaseTools {
    public int timeout = 40;
    protected static final Logger log = LoggerFactory.getLogger(BaseTools.class);

    /**
     * Finds a web element using the given locator.
     *
     * @param driver  WebDriver instance
     * @param locator By locator to identify the element
     * @return WebElement found using the locator
     */
    public WebElement find(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    /**
     * Finds all web elements matching the given locator.
     *
     * @param driver  WebDriver instance
     * @param locator By locator to identify elements
     * @return List of WebElements found using the locator
     */
    public List<WebElement> findAll(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }

    /**
     * Checks the visibility of a web element.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to check visibility
     */
    public void checkElementVisibility(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));

        } catch (TimeoutException t) {
            throw new NoSuchElementException("Element is invisible!");
        }
    }

    /**
     * Checks if a web element is clickable.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to check if clickable
     */
    public void checkElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (TimeoutException t) {
            throw new InvalidElementStateException("Element is not clickable!");
        }
    }

    /**
     * Clicks on an element when it is visible and clickable.
     *
     * @param driver      WebDriver instance
     * @param element     WebElement to be clicked
     * @param elementName Name of the element for logging purposes
     */
    public void clickOnElement(WebDriver driver, WebElement element, String elementName) {
        log.info("Click on " + elementName);
        checkElementVisibility(driver, element);
        checkElementClickable(driver, element);
        element.click();
    }

    /**
     * Types text into an input field when it is visible.
     *
     * @param driver  WebDriver instance
     * @param element WebElement representing the input field
     * @param text    Text to be typed into the field
     */
    public void typeText(WebDriver driver, WebElement element, String text) {
        log.info("Typing " + text);
        checkElementVisibility(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Checks if a web element is visible.
     *
     * @param element WebElement to check
     * @return true if the element is visible, false otherwise
     */
    public boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Gets the text content of a web element.
     *
     * @param driver  WebDriver instance
     * @param element WebElement to retrieve text from
     * @return Text content of the element
     */
    public String getElementText(WebDriver driver, WebElement element) {
        checkElementVisibility(driver, element);
        return element.getText();
    }

    /**
     * Retrieves a list of prices from web elements.
     *
     * @param driver        WebDriver instance
     * @param priceElements List of WebElements representing price elements
     * @return List of prices as integers
     */
    public List<Integer> getPricesList(WebDriver driver, List<WebElement> priceElements) {
        List<Integer> pricesList = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = getElementText(driver, element).replaceAll("Price:", "").replaceAll("Rs.", "").trim();
            int price = Integer.parseInt(priceText);
            pricesList.add(price);
        }
        return pricesList;
    }

    /**
     * Get the minimum price of the prices list
     *
     * @param pricesList
     * @return minimum price
     */
    public int getMinimumPrice(List<Integer> pricesList) {
        int minimumPrice = Collections.min(pricesList);
        return minimumPrice;
    }

    /**
     * Writes key-value data to a text file.
     *
     * @param key      Key to store
     * @param value    Value associated with the key
     * @param filePath Path to the file where data will be written
     */
    public void writeKeyValueToTextFile(String key, String value, String filePath) {
        Path file = Paths.get(filePath);
        try {
            String data = key + "=" + value + "\n";
            Files.writeString(file, data, java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes key-value data to a JSON file.
     *
     * @param keyValuePairs HashMap containing key-value pairs to be written
     * @param filePath      Path to the JSON file where data will be written
     */
    public void writeToJSONFile(HashMap<String, String> keyValuePairs, String filePath) {
        File file = new File(filePath);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try {
            // Read the existing content if the file is not empty
            if (file.exists() && file.length() > 0) {
                try (FileReader fileReader = new FileReader(filePath)) {
                    JSONParser jsonParser = new JSONParser();
                    jsonArray = (JSONArray) jsonParser.parse(fileReader);
                }
            }

            // Add the new key-value pairs to a JSONObject
            jsonObject.putAll(keyValuePairs);

            // Append the new JSONObject to the JSONArray
            jsonArray.add(jsonObject);

            // Write the updated JSONArray to the file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(jsonArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a value from a JSON file based on index and key.
     *
     * @param filePath Path to the JSON file
     * @param index    Index of the JSON object in the file
     * @param key      Key whose value needs to be retrieved
     * @return Value associated with the key, or null if not found
     */
    public String readJSONFileValue(String filePath, int index, String key) {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) jsonArray.get(index);
            return (String) jsonObject.get(key);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads a value from a text file.
     *
     * @param filePath Path to the text file
     * @param key      Key whose value needs to be retrieved
     * @return Value associated with the key, or null if not found
     */
    public String readValueFromTextFile(String filePath, String key) {
        Path file = Paths.get(filePath);
        try {
            HashMap<String, String> dataCollection = new HashMap<>();
            String fileContent = Files.readString(file);
            String[] lines = fileContent.split("\\r?\\n");
            for (String line : lines) {
                String[] data = line.split("=");
                if (data.length == 2) {
                    dataCollection.put(data[0], data[1]);
                }
            }
            if (dataCollection.containsKey(key)) {
                return dataCollection.get(key);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Compares an actual value with an expected value.
     *
     * @param actualValue   The actual value
     * @param expectedValue The expected value
     * @return true if both values match, false otherwise
     */
    public boolean isActualValueMatchingExpected(String actualValue, String expectedValue) {
        boolean isMatching;
        if (actualValue.equals(expectedValue)) {
            log.info("Actual value:" + actualValue + "\n" + "Expected value:" + expectedValue + "\nThe actual value is matching the expected value!");
            isMatching = true;
        } else {
            log.info("Actual value:" + actualValue + "\n" + "Expected value:" + expectedValue + "\nThe actual value is not matching the expected value!");
            isMatching = false;
        }
        return isMatching;
    }

    /**
     * Switches the driver to a specific iframe.
     *
     * @param driver  WebDriver instance
     * @param element WebElement representing the iframe
     */
    public void switchToFrame(WebDriver driver, WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * Switches the driver back to the main page.
     *
     * @param driver WebDriver instance
     */
    public void switchToMainPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    /**
     * Types text into an input field using JavaScriptExecutor when the field is visible.
     *
     * @param driver  WebDriver instance
     * @param element WebElement representing the input field
     * @param text    Text to be entered
     */
    public void typeTextJs(WebDriver driver, WebElement element, String text) {
        log.info("Typing " + text);
        checkElementVisibility(driver, element);
        element.clear();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value', arguments[1])", element, text);
    }

    /**
     * Deletes a JSON file from the specified file path.
     *
     * @param filePath Path to the JSON file that needs to be deleted
     */
    public void deleteJSONFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                log.info("File deleted successfully: " + filePath);
            } else {
                log.warn("Failed to delete file: " + filePath);
            }
        } else {
            log.warn("File not found: " + filePath);
        }
    }

}



