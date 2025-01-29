#language: en
@Test1
Feature: Buying two sunscreens

  Add two sunscreens to your cart. First, select the least expensive sunscreen that is SPF-50. For your second sunscreen, select the least expensive sunscreen that is SPF-30.
  Click on the cart when you are done. Verify that the shopping cart looks correct. Then, fill out your payment details and submit the form.

  Scenario Outline: Buy the two least expensive sunscreens that are SPF-50 and SPF-30 successively.
    Given I am on the Home page
    When I click on "Buy sunscreens" button
    And I verify that I am on Sunscreens page
    And I add sunscreens based on hint text conditions
    And I click on Cart
    And I verify that the sunscreens shopping cart looks correct
    And I fill the payment details with "<email>","<cardNumber>","<expirationDate>","<cvc>","<zipCode>"
    And I submit the form
    Then the message "Your payment was successful. You should receive a follow-up call from our sales team." is displayed

    Examples:
      | email               | cardNumber       | expirationDate | cvc | zipCode |
      | test-auto@gmail.com | 4242424242424242 | 06/27          | 123 | 10589   |


