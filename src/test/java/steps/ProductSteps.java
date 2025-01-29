package steps;

import configuration.SetupTearDown;
import io.cucumber.java.en.And;
import pages.ProductsPage;

public class ProductSteps {
    ProductsPage productsPage;
    SetupTearDown std;

    public ProductSteps() {
        std = new SetupTearDown();
        productsPage = new ProductsPage(std.getDriver());
    }

    @And("I add skincare products based on hint text conditions")
    public void iAddSkincareProductsBasedOnHintTextConditions() {
        productsPage.addProductBasedOnType();
    }
}
