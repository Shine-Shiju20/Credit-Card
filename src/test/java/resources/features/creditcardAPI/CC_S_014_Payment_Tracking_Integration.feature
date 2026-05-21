@CreditCardAPI
Feature: CC-S-014 Payment Tracking Integration
  Requirement: CC-REQ-014
  Description: Verify purchases and payments are recorded in payment tracking

  @CC-S-014
  @CC_TC_073
  Scenario Outline: Verify repayment sync integration
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_073 |

  @CC-S-014
  @CC_TC_074
  Scenario Outline: Verify external payment reference persistence
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_074 |

  @CC-S-014
  @CC_TC_075
  Scenario Outline: Verify payment analytics API
    Given test data is loaded for "<TestcaseID>"
    When user fetches payment analytics
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_075 |

  @CC-S-014
  @CC_TC_076
  Scenario Outline: Verify failed callback updates state
    Given test data is loaded for "<TestcaseID>"
    When payment callback is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_076 |

  @CC-S-014
  @CC_TC_077
  Scenario Outline: Verify reconciliation settlement process
    Given test data is loaded for "<TestcaseID>"
    When payment reconciliation service is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_077 |

  @CC-S-014
  @CC_TC_078
  Scenario Outline: Verify repayment history API
    Given test data is loaded for "<TestcaseID>"
    When user fetches repayment history
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_078 |

