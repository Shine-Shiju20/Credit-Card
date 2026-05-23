@CreditCardAPI
Feature: CC-S-007 Block & Unblock
  Requirement: CC-REQ-007
  Description: Test blocking an active card and unblocking a blocked card

  @CC-S-007
  @CC_TC_048
  Scenario Outline: Verify card block success
    Given test data is loaded for "<TestcaseID>"
    When user blocks the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_048 |

  @CC-S-007
  @CC_TC_049
  Scenario Outline: Verify card unblock success
    Given test data is loaded for "<TestcaseID>"
    When user unblocks the credit card
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_049 |

  @CC-S-007
  @CC_TC_050
  Scenario Outline: Verify blocked card purchase rejection
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_050 |