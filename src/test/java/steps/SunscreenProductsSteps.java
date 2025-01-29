package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.And;
import pages.SunscreensPage;

public class SunscreenProductsSteps {
    SunscreensPage sunscreensPage;
    SetupTearDown std;

    public SunscreenProductsSteps() {
        std = new SetupTearDown();
        sunscreensPage = new SunscreensPage(std.getDriver());
    }

    @And("I verify that I am on Sunscreens page")
    public void i_verify_that_I_am_on_Sunscreens_page() {
        sunscreensPage.verifySunscreensPage();
    }

    @And("I add sunscreens based on hint text conditions")
    public void iAddSunscreensBasedOnHintTextConditions() {
        sunscreensPage.addSunscreens();
    }

    @And("I click on Cart button")
    public void i_click_on_Cart_button() {
        sunscreensPage.clickOnCart();
    }

}
