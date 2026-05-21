@CreditCardAPI
Feature: CC-S-006 Balance Repayment
  Requirement: CC-REQ-006
  Description: Test making payments against outstanding balance, partial and full repayment

  @CC-S-006
  @CC_TC_045
  Scenario Outline: Verify full repayment clears outstanding balance
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200
    And response field "data.new_outstanding_balance" should equal "0.0"

    Examples:
      | TestcaseID |
      | CC_TC_045  |

  @CC-S-006
  @CC_TC_046
  Scenario Outline: Verify partial repayment updates outstanding correctly
    Given test data is loaded for "<TestcaseID>"
    When user performs repayment transaction
    Then API response status code should be 200
    And response field "data.new_outstanding_balance" should equal "15000.0"

    Examples:
      | TestcaseID |
      | CC_TC_046  |

  # SKIPPED: Source account balance not validated by repayment API (Cat 2 backend gap)
  # @CC_TC_047

  # SKIPPED: UI repayment toast validation
  # @CC_TC_048
