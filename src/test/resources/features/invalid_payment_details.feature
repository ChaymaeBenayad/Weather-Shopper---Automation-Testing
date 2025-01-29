#language: en
@Test3
Feature: Shop for moisturizers or sunscreens based on current temperature

  Scenario Outline: Filling the form with invalid payment details.
    Given I am on the Home page
    When I navigate to Moisturizers or Sunscreens page based on current temperature
    And I add skincare products based on hint text conditions
    And I click on Cart
    And I verify that the shopping cart looks correct
    And I fill the payment details with "<email>","<cardNumber>","<expirationDate>","<cvc>","<zipCode>"
    And I submit the form
    Then I stay on the form page
    Examples:
      | email               | cardNumber       | expirationDate | cvc | zipCode |
      | test-auto.gmail.com | 4242424242424242 | 06/27          | 123 | 10589   |
      | test-auto@gmail.com | 4242424242424242 | 05/20          | 123 | 10589   |
      | test-auto@gmail.com | 4242424242424242 | 06/27          | 12  | 10589   |
      | test-auto@gmail.com | 4242424242424242 | 06/27          | 123 |         |