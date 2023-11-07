Feature: Login
  As a customer
  I want to login

  Background:
    Given a customer with a account username "test123" and password "test123" login

  Scenario: Correct username and password
    When I login with a username "test123" and password "test123"
    Then I can login

  Scenario: Incorrect username and password
    When I login with a username "test123" and password "test124"
    Then I cannot login