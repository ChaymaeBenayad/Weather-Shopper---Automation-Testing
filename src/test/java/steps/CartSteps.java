package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CartPage;

public class CartSteps {

    CartPage cartPage;
    SetupTearDown std;

    public CartSteps() {
        std = new SetupTearDown();
        cartPage = new CartPage(std.getDriver());
    }

    @And("I verify that the sunscreens shopping cart looks correct")
    public void iVerifyThatTheSunscreensShoppingCartLooksCorrect() {
        cartPage.verifySunscreensCart();
    }

    @And("I fill the payment details with {string},{string},{string},{string},{string}")
    public void iFillThePaymentDetailsWith(String email, String cardNumber, String expirationDate, String cvc, String zipCode) {
        cartPage.fillTheForm(email, cardNumber, expirationDate, cvc, zipCode);
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        cartPage.submitForm();
    }

    @And("I verify that the moisturizers shopping cart looks correct")
    public void iVerifyThatTheMoisturizersShoppingCartLooksCorrect() {
        cartPage.verifyMoisturizersCart();
    }

    @And("I verify that the shopping cart looks correct")
    public void iVerifyThatTheShoppingCartLooksCorrect() {
        cartPage.verifyCartBasedOnType();
    }

    @Then("I stay on the form page")
    public void iStayOnTheFormPage() {
        cartPage.verifyIamOnForm();
    }
}
