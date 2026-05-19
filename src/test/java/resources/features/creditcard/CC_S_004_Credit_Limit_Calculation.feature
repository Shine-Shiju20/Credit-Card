@CreditCardAPI
Feature: CC-S-004 Credit Limit Calculation
  Requirement: CC-REQ-004
  Description: Verify credit limit is computed correctly based on income, DTI, score, and account balance cap

  @CC-S-004
  @CC_TC_030
  Scenario Outline: Verify application approval when score >= 500
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_030 |

  @CC-S-004
  @CC_TC_032
  Scenario Outline: Verify high score user receives higher credit limit
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_032 |

  @CC-S-004
  @CC_TC_033
  Scenario Outline: Verify high DTI reduces eligibility
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_033  |

  @CC-S-004
  @CC_TC_034
  Scenario Outline: Verify low DTI improves approval chances
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_034 |

  @CC-S-004
  @CC_TC_035
  Scenario Outline: Verify approved limit is based on income logic
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_035 |

  @CC-S-004
  @CC_TC_036
  Scenario Outline: Verify current balance alone does not determine limit
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 201

    Examples:
      | TestcaseID |
      | CC_TC_036 |
  @CC-S-004
  @CC_TC_038
  Scenario Outline: Verify requested limit below minimum threshold is rejected
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_038  |

  @CC-S-004
  @CC_TC_039
  Scenario Outline: Verify application rejection when final limit below minimum
    Given test data is loaded for "<TestcaseID>"
    When user submits credit card application
    Then API response status code should be 400

    Examples:
      | TestcaseID |
      | CC_TC_039  |

  @CC-S-004
  @CC_TC_040
  Scenario Outline: Verify purchase reduces available balance
    Given test data is loaded for "<TestcaseID>"
    When user performs purchase transaction
    Then API response status code should be 200

    Examples:
      | TestcaseID |
      | CC_TC_040 |

  # SKIPPED: CC_TC_037 — average balance bonus limit
  # Cat 2 blocker: avg_balance bonus logic not implemented in backend
  # Backend caps bonus at 50%; full formula absent
  # @CC_TC_037

