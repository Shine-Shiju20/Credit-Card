@CreditCardAPI
Feature: CC-S-019 Transaction Edge Cases
  Requirement: CC-REQ-019
  Description: Verify transaction validations, transaction state handling, and purchase edge cases

  @CC-S-019
  @CC_TC_107
  Scenario Outline: Verify transaction fails for insufficient available limit
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_107 |

  @CC-S-019
  @CC_TC_108
  Scenario Outline: Verify transaction succeeds within available limit
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_108 |


  @CC-S-019
  @CC_TC_111
  Scenario Outline: Verify transaction blocked for closed credit card
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_111 |

  @CC-S-019
  @CC_TC_112
  Scenario Outline: Verify transaction blocked for blocked credit card
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_112 |

  @CC-S-019
  @CC_TC_113
  Scenario Outline: Verify transaction merchant validation handling
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_113 |