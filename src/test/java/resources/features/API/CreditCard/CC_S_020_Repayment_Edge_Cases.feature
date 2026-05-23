@CreditCardAPI
Feature: CC-S-020 Repayment Edge Cases
  Requirement: CC-REQ-020
  Description: Verify repayment validations, repayment failures, and repayment edge case handling

  @CC-S-020
  @CC_TC_114
  Scenario Outline: Verify successful minimum due repayment
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_114 |

  @CC-S-020
  @CC_TC_115
  Scenario Outline: Verify successful full outstanding repayment
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_115 |

  @CC-S-020
  @CC_TC_116
  Scenario Outline: Verify repayment rejection for insufficient balance
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_116 |

  @CC-S-020
  @CC_TC_117
  Scenario Outline: Verify repayment rejection for invalid repayment amount
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_117 |

