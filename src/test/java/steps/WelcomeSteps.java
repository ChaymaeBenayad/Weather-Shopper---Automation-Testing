package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import pages.WelcomePage;

public class WelcomeSteps {
    WelcomePage welcomePage;
    SetupTearDown std;

    public WelcomeSteps() {
        std = new SetupTearDown();
        welcomePage = new WelcomePage(std.getDriver());
    }

    @Given("I am on the Home page")
    public void iAmOnTheHomePage() {
        welcomePage.verifyPageHeader();
    }

    @And("I click on {string} button")
    public void iClickOnButton(String btnTxt) {
        welcomePage.clickOnButton(btnTxt);
    }

    @When("I navigate to Moisturizers or Sunscreens page based on current temperature")
    public void iNavigateToMoisturizersOrSunscreensPageBasedOnCurrentTemperature() {
        welcomePage.checkCurrentTemperature();
    }
}
