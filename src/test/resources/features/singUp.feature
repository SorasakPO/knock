Feature: Sing Up
  As a customer
  I want to sing up
  so that I can do login

  Background:
    Given the user is on the sign-up page

  @PositiveCase
  Scenario: User provides valid registration information
    When the user enters valid registration information
    And submits the registration form
    Then the user should see a Successfully signed up! Please login

  @NegativeCase
  Scenario: User provides invalid registration information
    When the user enters invalid registration information
    And submits the registration form
    Then the user should see an error message
    And the user should remain on the sign-up page
