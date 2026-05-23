Feature: Responsive UI Validation

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page

  @ResponsiveValidation
  Scenario: Verify profile data is displayed properly in different screen sizes
    When User changes browser screen size
    Then Updated profile data should be visible properly