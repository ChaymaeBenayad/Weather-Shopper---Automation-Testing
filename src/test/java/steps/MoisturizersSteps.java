package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.And;
import pages.MoisturizersPage;

public class MoisturizersSteps {
    MoisturizersPage moisturizersPage;
    SetupTearDown std;

    public MoisturizersSteps() {
        std = new SetupTearDown();
        moisturizersPage = new MoisturizersPage(std.getDriver());
    }

    @And("I verify that I am on Moisturizers page")
    public void i_verify_that_I_am_on_Moisturizers_page() {
        moisturizersPage.verifyMoisturizersPage();
    }

    @And("I add moisturizers based on hint text conditions")
    public void i_add_moisturizers_based_on_hint_text_conditions() {
        moisturizersPage.addMoisturizers();
    }

    @And("I click on Cart")
    public void i_click_on_Cart() {
        moisturizersPage.clickOnCart();
    }
}
