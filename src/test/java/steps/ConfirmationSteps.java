package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.Then;
import pages.ConfirmationPage;


public class ConfirmationSteps {
    ConfirmationPage confirmationPage;
    SetupTearDown std;

    public ConfirmationSteps() {
        std = new SetupTearDown();
        confirmationPage = new ConfirmationPage(std.getDriver());
    }

    @Then("the message {string} is displayed")
    public void theMessageIsDisplayed(String message) {
        confirmationPage.checkConfirmationMessage(message);
    }

}
