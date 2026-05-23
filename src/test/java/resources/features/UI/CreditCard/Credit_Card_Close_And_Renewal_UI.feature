@CreditCardUI
Feature: Credit Card Close And Renewal UI

  Background:
    Given User launches the application
    And User logs into the banking application using "yilap59703@deapad.com" and "Kuttichatan@123"
    And User navigates to Credit Cards page

  @CC_TC_085
  Scenario: Verify UI close confirm modal closes zero-balance card
    When User flips the credit card
    And User clicks Close Card button
    Then Close Card confirmation modal should be displayed
    When User confirms card closure
    Then Credit card should be closed successfully

  @CC_TC_086
  Scenario: Verify UI shows outstanding balance warning before closing
    When User flips a card with outstanding balance
    And User clicks Close Card button
    Then Outstanding balance warning should be displayed

  @CC_TC_087
  Scenario: Verify closed card does not show Close Card button in UI
    When User flips the credit card
    And User clicks Close Card button
    Then Close Card confirmation modal should be displayed
    When User confirms card closure
    Then Credit card should be closed successfully
    When User scrolls to bottom of Credit Cards page
#    And User flips a closed credit card
    Then Delete Card button should be visible
    And Close Card button should not be visible

  @CC_TC_105
  Scenario: Verify card renewal creates new card and deactivates old card in UI
    When User opens expired credit card details
    And User clicks Renew Card button
    Then New renewed card should be displayed
    And Old card should be shown as deactivated

  @CC_TC_106
  Scenario: Verify renewal on non-expired card is too early in UI
    When User opens non expired credit card details
    And User clicks Renew Card button
    Then Renewal too early validation message should be displayed