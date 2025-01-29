#language: en
@Test2
Feature: Buying two moisturizers

  Add two moisturizers to your cart. First, select the least expensive moisturizer that contains Aloe. For your second moisturizer, select the least expensive moisturizer that contains almond.
  Click on cart when you are done. Verify that the shopping cart looks correct. Then, fill out your payment details and submit the form.

  Scenario Outline: Buy the two least expensive moisturizers that contain Aloe and Almond successively.
    Given I am on the Home page
    When I click on "Buy moisturizers" button
    And I verify that I am on Moisturizers page
    And I add moisturizers based on hint text conditions
    And I click on Cart button
    And I verify that the moisturizers shopping cart looks correct
    And I fill the payment details with "<email>","<cardNumber>","<expirationDate>","<cvc>","<zipCode>"
    And I submit the form
    Then the message "Your payment was successful. You should receive a follow-up call from our sales team." is displayed

    Examples:
      | email               | cardNumber       | expirationDate | cvc | zipCode |  |
      | test-auto@gmail.com | 4242424242424242 | 06/27          | 123 | 10589   |  |
