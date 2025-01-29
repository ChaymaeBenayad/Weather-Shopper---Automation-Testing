#language: en
@Test
Feature: Shop for moisturizers or sunscreens based on current temperature

  Scenario Outline: Shop for moisturizers if the weather is below 19 degrees or shop for sunscreens if the weather is above 34 degrees.
    Given I am on the Home page
    When I navigate to Moisturizers or Sunscreens page based on current temperature
    And I add skincare products based on hint text conditions
    And I click on Cart
    And I verify that the shopping cart looks correct
    And I fill the payment details with "<email>","<cardNumber>","<expirationDate>","<cvc>","<zipCode>"
    And I submit the form
    Then the message "Your payment was successful. You should receive a follow-up call from our sales team." is displayed
    Examples:
      | email               | cardNumber       | expirationDate | cvc | zipCode |
      | test-auto@gmail.com | 4242424242424242 | 06/27          | 123 | 10589   |