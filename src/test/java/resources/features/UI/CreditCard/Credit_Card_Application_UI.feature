@CreditCardUI
Feature: Credit Card Application UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_001
  Scenario: Verify user can open new credit card application form
    When User clicks on Apply Credit Card button
    Then Credit Card Application modal should open successfully

  @CC_TC_002
  Scenario: Verify linked bank accounts are displayed in dropdown
    When User clicks on Apply Credit Card button
    Then Linked bank accounts should be displayed in dropdown

  @CC_TC_003
  Scenario: Verify only ACTIVE accounts are shown in linked account dropdown
    When User clicks on Apply Credit Card button
    Then Only active linked accounts should be displayed


  @CC_TC_005
  Scenario Outline: Verify card type dropdown displays available card types
    When User clicks on Apply Credit Card button
    And User clicks on card tier dropdown
    Then User should see card type "<CardType>" in dropdown

    Examples:
      | CardType            |
      | Entry (Classic)     |
      | Premium (Privilege) |

  @CC_TC_006
  Scenario: Verify Entry card can be selected successfully
    When User clicks on Apply Credit Card button
    And User selects "Entry (Classic)" card type
    Then Selected card type should be "Entry (Classic)"

  @CC_TC_007
  Scenario: Verify Premium card can be selected successfully
    When User clicks on Apply Credit Card button
    And User selects "Premium (Privilege)" card type
    Then Selected card type should be "Premium (Privilege)"

  @CC_TC_008
  Scenario: Verify requested credit limit field accepts valid numeric value
    When User clicks on Apply Credit Card button
    And User enters requested limit "25000"
    Then Requested limit should be accepted

  @CC_TC_009
  Scenario: Verify requested credit limit field rejects alphabets
    When User clicks on Apply Credit Card button
    And User enters requested limit "abc"
    Then Requested limit field should remain empty

  @CC_TC_010
  Scenario: Verify requested credit limit field rejects special characters
    When User clicks on Apply Credit Card button
    And User enters requested limit "@@@"
    Then Requested limit field should remain empty

  @CC_TC_011
  Scenario: Verify requested credit limit field rejects negative values
    When User clicks on Apply Credit Card button
    And User enters requested limit "-88776"
    And User clicks Continue button
    And User clicks Continue button
    And User clicks Submit Application button
    Then Requested credit limit validation message "Requested limit must be a positive number" should be displayed

  @CC_TC_012
  Scenario: Verify requested credit limit field rejects zero value
    When User clicks on Apply Credit Card button
    And User enters requested limit "0"
    And User clicks Continue button
    And User clicks Continue button
    And User clicks Submit Application button
    Then Requested credit limit validation message "Requested limit must be a positive number" should be displayed

  @CC_TC_013
  Scenario: Verify requested limit below 10000 is rejected
    When User clicks on Apply Credit Card button
    And User enters requested limit "5000"
    Then Requested credit limit validation message "Requested limit must be at least 10000" should be displayed

  @CC_TC_014
  Scenario: Verify requested limit exactly 10,000 is accepted
    When User clicks on Apply Credit Card button
    And User enters requested limit "10000"
    Then Requested limit should be accepted

  @CC_TC_015
  Scenario: Verify extremely high requested limit is validated properly
    When User clicks on Apply Credit Card button
    And User enters requested limit "999999999"
    And User clicks Continue button
    Then Maximum eligible limit validation message should be displayed

  @CC_TC_016
  Scenario: Verify empty requested limit field validation
    When User clicks on Apply Credit Card button
    And User leaves requested limit field empty
    And User clicks Continue button
    Then Empty requested limit validation message should appear

  @CC_TC_017
  Scenario: Verify decimal limit values are handled correctly
    When User clicks on Apply Credit Card button
    And User enters requested limit "25000.50"
    Then Requested limit should be accepted

  @CC_TC_018
  Scenario: Verify Continue button works after valid inputs
    When User clicks on Apply Credit Card button
    And User selects "Entry (Classic)" card type
    And User enters requested limit "25000"
    And User selects valid source account
    And User clicks Continue button
    Then Eligibility Check step should be displayed

  @CC_TC_019
  Scenario: Verify Continue button is disabled for invalid inputs
    When User clicks on Apply Credit Card button
    And User enters requested limit "a@c"
    Then Continue button should be disabled

  @CC_TC_020
  Scenario: Verify Cancel button closes application modal
    When User clicks on Apply Credit Card button
    And User clicks Cancel button
    Then Apply Credit Card modal should close