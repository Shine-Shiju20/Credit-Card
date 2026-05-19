@CreditCardAPI
Feature: CC-S-003 Eligibility Failures
  Requirement: CC-REQ-003
  Description: Test all ineligibility scenarios: unverified KYC, low income, age out of range, insufficient balance

  @CC-S-003
  @CC_TC_022
  Scenario Outline: Verify non-verified KYC user cannot apply
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_022  |

  @CC-S-003
  @CC_TC_024
  Scenario Outline: Verify underage rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_024 |

  @CC-S-003
  @CC_TC_025
  Scenario Outline: Verify overage user cannot apply
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_025  |

  @CC-S-003
  @CC_TC_027
  Scenario Outline: Verify entry card rejection with income below 3L
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_027  |

  @CC-S-003
  @CC_TC_029
  Scenario Outline: Verify premium card rejection with income below 10L
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_029  |

  @CC-S-003
  @CC_TC_031
  Scenario Outline: Verify low credit score and high DTI causes rejection
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_031  |

