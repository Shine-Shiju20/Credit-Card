@CreditCardAPI
Feature: CC-S-015 Security & Authorization
  Requirement: CC-REQ-015
  Description: Verify unauthorized access is blocked and only card owner can perform operations

  @CC-S-015
  @CC_TC_088
  Scenario Outline: Verify unauthorized access restriction
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_088 |

  @CC-S-015
  @CC_TC_089
  Scenario Outline: Verify expired token rejection
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 401

    Examples:
      | TestcaseID |
      | CC_TC_089 |

  @CC-S-015
  @CC_TC_090
  Scenario Outline: Verify blocked session repayment rejection
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_090 |

  @CC-S-015
  @CC_TC_091
  Scenario Outline: Verify admin operation restriction
    Given test data is loaded for "<TestcaseID>"
    When user deletes the credit card
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_091 |

  @CC-S-015
  @CC_TC_092
  Scenario Outline: Verify authorization context audit logs
    Given test data is loaded for "<TestcaseID>"
    When audit logging service is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_092 |

  @CC-S-015
  @CC_TC_093
  Scenario Outline: Verify sensitive details masked
    Given test data is loaded for "<TestcaseID>"
    When user fetches credit card details
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_093 |

  @CC-S-015
  @CC_TC_094
  Scenario Outline: Verify admin cannot manage customer card via customer endpoint
    Given test data is loaded for "<TestcaseID>"
    When user closes the credit card
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_094  |

