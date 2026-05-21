@CreditCardAPI
Feature: CC-S-005 Purchase Transaction
  Requirement: CC-REQ-005
  Description: Simulate purchases within and beyond available credit limit

  @CC-S-005
  @CC_TC_041
  Scenario Outline: Verify inactive card purchase blocked
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_041 |

  @CC-S-005
  @CC_TC_042
  Scenario Outline: Verify insufficient limit purchase blocked
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_042 |

  @CC-S-005
  @CC_TC_043
  Scenario Outline: Verify merchant category persisted
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_043 |

  # SKIPPED: CC_TC_044 — expired card purchase blocked
  # Cat 2 blocker: expiry_date field not present in DB schema
  # Cannot seed or assert expired card state via API
  # @CC_TC_044
