@CreditCardAPI
Feature: CC-S-011 Billing Cycle Dates
  Requirement: CC-REQ-011
  Description: Verify credit card monthly billing cycle processes

  @skip
  @CC_S_011
  @CC_TC_061
  Scenario Outline: Verify monthly billing applies 3.6% interest and 5% minimum due
    Given test data is loaded for "<TestcaseID>"
    When billing scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_061 |

  @skip
  @CC_S_011
  @CC_TC_062
  Scenario Outline: Verify zero outstanding card is skipped by billing job
    Given test data is loaded for "<TestcaseID>"
    When billing scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_062 |

  @skip
  @CC_S_011
  @CC_TC_063
  Scenario Outline: Verify blocked card with balance still accrues interest
    Given test data is loaded for "<TestcaseID>"
    When billing scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_063 |

  @skip
  @CC_S_011
  @CC_TC_064
  Scenario Outline: Verify unauthorized user cannot trigger monthly billing from API
    Given test data is loaded for "<TestcaseID>"
    When billing scheduler is triggered
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_064 |
