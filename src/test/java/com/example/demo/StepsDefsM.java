package com.example.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

public class StepsDefsM {

    @Given("the user is on the sign-up page")
    public void givenUserIsOnSignUpPage() {
        // Navigate to the sign-up page
    }

    @When("the user enters valid registration information")
    public void whenUserEntersValidInfo() {
        // Enter valid registration information
    }

    @When("submits the registration form")
    public void whenUserSubmitsForm() {
        // Submit the form
    }

    @Then("the user should see a Successfully signed up! Please login")
    public void thenUserIsRedirected() {
        // Verify user is redirected to the home page
    }


    @When("the user enters invalid registration information")
    public void whenUserEntersInvalidInfo() {
        // Enter invalid registration information
    }

    @Then("the user should see an error message")
    public void thenUserSeesErrorMessage() {
        // Verify error message is displayed
    }

    @Then("the user should remain on the sign-up page")
    public void thenUserRemainsOnSignUpPage() {
        // Verify the user is still on the sign-up page
    }
    
}
