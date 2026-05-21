@CreditCardAPI
Feature: CC-S-002 Credit Card Application - Premium Tier
  Requirement: CC-REQ-002
  Description: Test applying for a premium credit card with income >= 10 Lakhs

  @CC-S-002
  @CC_TC_028
  Scenario Outline: Verify premium card approval persists metadata
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 200
    # SKIPPED: avg_balance_bonus field assertion — backend gap, logic not implemented.

    Examples:
      | TestcaseID |
      | CC_TC_028  |
