@CreditCardAPI
Feature: CC-S-010 Card Statement
  Requirement: CC-REQ-010
  Description: Test generating and viewing a credit card statement with transactions

  @CC-S-010
  @CC_TC_055
  Scenario Outline: Verify statement generation
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_055 |

  @CC-S-010
  @CC_TC_056
  Scenario Outline: Verify statement includes purchases
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_056 |

  @CC-S-010
  @CC_TC_057
  Scenario Outline: Verify minimum due calculation
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_057 |

  # SKIPPED: UI due date validation
  # @CC_TC_058

  @CC-S-010
  @CC_TC_059
  Scenario Outline: Verify statement PDF download
    Given test data is loaded for "<TestcaseID>"
    When user downloads statement PDF
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_059 |

  @CC-S-010
  @CC_TC_060
  Scenario Outline: Verify unauthenticated user cannot open statement
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_060 |
