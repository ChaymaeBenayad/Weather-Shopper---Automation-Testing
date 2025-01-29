package configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BaseTools;


public class SetupTearDown {
    public static WebDriver driver;
    protected static final Logger log = LoggerFactory.getLogger(SetupTearDown.class);

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        // Create driver
        log.info("Create chrome driver");
        // Create ChromeOptions instance
        ChromeOptions options = new ChromeOptions();

        // Disable Chrome popups
        options.addArguments("disable-popup-blocking");
        options.addArguments("incognito");

        // Automatically download and set up the ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        // open login page
        ConfigFileReader configFileReader = new ConfigFileReader();
        driver.get(configFileReader.getApplicationUrl());

        // maximize browser window
        driver.manage().window().maximize();

        // delete JSON file
        BaseTools baseTools = new BaseTools();
        baseTools.deleteJSONFile("src/test/java/utils/products.json");
    }

    @After()
    public void tearDown() {
        log.info("Close driver");
        // Close browser
        driver.quit();
    }
}
