Feature: Fixed Deposit functionality

  Background:
    Given user launches banking application
    And user logs in with valid credentials
    And user navigates to fixed deposit page

  @tc_33
  Scenario Outline: Verify FD dashboard load performance
    When user opens fixed deposit page and measures load time
    Then FD dashboard performance should be within "<maxLoadTime>" milliseconds

    Examples:
      | maxLoadTime |
      | 5000        |

  @tc_34
  Scenario Outline: Verify FD detail navigation
    When user creates FD with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    And user performs FD action "<fdAction>"
    Then navigation result should be "<expectedPage>"

    Examples:
      | accountType | amount | tenure                           | fdAction      | expectedPage |
      | current     | 5000   | 24 months (8.5% p.a.) - 2 years | View Details  | FD details   |

  @tc_35
  Scenario Outline: Verify multiple active FDs display correctly
    When user creates "<fdCount>" FDs with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD cards display status should be "<expectedResult>"

    Examples:
      | fdCount | accountType | amount | tenure                           | expectedResult |
      | 2       | any         | 1000   | 12 months (7% p.a.) - 1 year    | displayed      |

  @tc_36
  Scenario Outline: Verify FD page scrolling behavior
    Then FD page scroll behavior should be "<scrollStatus>"

    Examples:
      | scrollStatus |
      | scrollable   |
