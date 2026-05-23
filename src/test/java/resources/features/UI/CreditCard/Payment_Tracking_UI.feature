@CreditCardUI
Feature: Payment Tracking UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"

  @CC_TC_076
  Scenario: Verify UI PaymentTrackingPage lists credit card purchase records
    When User navigates to Payment Tracking page
    Then Credit card purchase records should be displayed

  @CC_TC_077
  Scenario: Verify UI analytics cards show payment tracking totals
    When User navigates to Payment Tracking page
    Then Payment tracking analytics cards should show credit card totals

  @CC_TC_078
  Scenario: Verify unauthenticated user cannot access payment tracking UI
    Given User launches the application
    When User navigates directly to Payment Tracking page without login
    Then User should be redirected to login page