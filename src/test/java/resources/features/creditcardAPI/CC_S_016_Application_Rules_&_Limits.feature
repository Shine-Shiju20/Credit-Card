@CreditCardAPI
Feature: CC-S-016 Application Rules & Limits
  Requirement: CC-REQ-016
  Description: Verify application-level rules, card ownership limits, and duplicate application restrictions

  @CC-S-016
  @CC_TC_095
  Scenario Outline: Verify user cannot exceed maximum active credit cards
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_095 |

  @CC-S-016
  @CC_TC_096
  Scenario Outline: Verify duplicate credit card application prevention
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_096 |

  @CC-S-016
  @CC_TC_097
  Scenario Outline: Verify user can apply after previous rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_097 |

  @CC-S-016
  @CC_TC_098
  Scenario Outline: Verify application blocked for blacklisted customer
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 403

    Examples:
      | TestcaseID |
      | CC_TC_098 |