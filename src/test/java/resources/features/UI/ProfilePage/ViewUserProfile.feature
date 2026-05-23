Feature: View User Profile Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page

  @ProfilePageLoad @TC_1
  Scenario: Verify profile page loads successfully
    Then User profile page should load successfully

  @ExpiredSession @TC_2
  Scenario: Verify expired session handling in profile page
    When User clicks on Logout button
    And User manually navigates to Profile page URL
    Then User should get redirected to Home page