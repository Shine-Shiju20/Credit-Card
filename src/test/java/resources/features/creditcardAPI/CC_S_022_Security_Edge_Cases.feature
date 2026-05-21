@CreditCardAPI
Feature: CC-S-022 Security Edge Cases
  Requirement: CC-REQ-022
  Description: Verify edge-case security validations, invalid access attempts, and restricted operations

  @CC-S-022
  @CC_TC_124
  Scenario Outline: Verify unauthorized user cannot access another user's card details
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_124 |

  @CC-S-022
  @CC_TC_125
  Scenario Outline: Verify invalid token blocks repayment operation
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_125 |

  @CC-S-022
  @CC_TC_126
  Scenario Outline: Verify unauthorized statement access restriction
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_126 |

  @CC-S-022
  @CC_TC_127
  Scenario Outline: Verify blocked user session access restriction
    Given test data is loaded for "<TestcaseID>"
    When user fetches user credit cards
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_127 |