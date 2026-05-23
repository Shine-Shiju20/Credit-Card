@CreditCardAPI
Feature: CC-S-021 Statement Filters
  Requirement: CC-REQ-021
  Description: Verify credit card statement retrieval and statement filter behavior

  @CC-S-021
  @CC_TC_119
  Scenario Outline: Verify statement retrieval by credit card id
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_119  |

  @CC-S-021
  @CC_TC_120
  Scenario Outline: Verify statement retrieval with date range filter
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_120  |

  @CC-S-021
  @CC_TC_121
  Scenario Outline: Verify statement retrieval with invalid date range
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_121  |

  @CC-S-021
  @CC_TC_122
  Scenario Outline: Verify statement retrieval with empty transaction history
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_122  |

  @CC-S-021
  @CC_TC_123
  Scenario Outline: Verify statement retrieval for closed card
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card statement
    Then API response status code should be 304

    Examples:
      | TestcaseID |
      | CC_TC_123  |