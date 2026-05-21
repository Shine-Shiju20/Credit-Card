@CreditCardAPI
Feature: CC-S-023 System & Data Integrity
  Requirement: CC-REQ-023
  Description: Verify system integrity, data consistency, and backend operational reliability

  @CC-S-023
  @CC_TC_128
  Scenario Outline: Verify credit card data consistency after multiple operations
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_128 |

  @CC-S-023
  @CC_TC_129
  Scenario Outline: Verify repayment history consistency after successful payment
    Given test data is loaded for "<TestcaseID>"
    When user fetches repayment history
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_129 |

  @CC-S-023
  @CC_TC_130
  Scenario Outline: Verify system stability during repeated statement retrieval
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_130 |