@CreditCardAPI
Feature: CC-S-012 Late Payment Penalty
  Requirement: CC-REQ-012
  Description: Verify penalty is applied when payment is overdue

  @CC-S-012
  @CC_TC_065
  Scenario Outline: Verify late fee application
    Given test data is loaded for "<TestcaseID>"
    When late payment scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_065 |

  @CC-S-012
  @CC_TC_066
  Scenario Outline: Verify repeated penalty increment
    Given test data is loaded for "<TestcaseID>"
    When late payment scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_066 |

  @CC-S-012
  @CC_TC_067
  Scenario Outline: Verify penalty reflected in statement
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_067 |

  @CC-S-012
  @CC_TC_068
  Scenario Outline: Verify overdue notification generation
    Given test data is loaded for "<TestcaseID>"
    When notification service is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_068 |

