Feature: Fixed Deposit functionality

  Background:
    Given user launches banking application
    And user logs in with valid credentials
    And user navigates to fixed deposit page

  @tc_37
  Scenario Outline: Verify decimal deposit amount handling
    When user clicks "<action>"
    And user enters decimal FD amount "<amount>"
    Then decimal amount handling should be "<expectedResult>"

    Examples:
      | action        | amount  | expectedResult |
      | Create New FD | 2500.75 | accepted       |

#  @tc_38
#  Scenario Outline: Verify FD creation with exact available balance
#    When user clicks "<action>"
#    And user selects account type "<accountType>"
#    And user enters amount type "<amountType>"
#    And user selects tenure option "<tenure>"
#    And user clicks "<submitAction>"
#    Then FD creation result should be "<expectedResult>"
#
#    Examples:
#      | action        | accountType | amountType       | tenure                        | submitAction     | expectedResult |
#      | Create New FD | current     | exact balance    | 12 months (7% p.a.) - 1 year | Create FD Submit | successful     |

  @tc_39
  Scenario Outline: Verify custom non-step deposit amount handling
    When user clicks "<action>"
    And user selects account type "<accountType>"
    And user enters custom deposit amount "<amount>"
    Then custom deposit amount handling should be "<expectedResult>"

    Examples:
      | action        | accountType | amount | expectedResult |
      | Create New FD | current     | 3589   | accepted       |

  @tc_40
  Scenario Outline: Verify Close FD option availability for active FD accounts
    When user opens FD details for "<fdStatus>" FD account
    Then Close FD option visibility should be "<expectedVisibility>"

    Examples:
      | fdStatus | expectedVisibility |
      | Active   | visible            |

  @tc_41
  Scenario Outline: Verify FD creation limit enforcement per user
    When user attempts FD creation beyond limit with account type "<accountType>", amount "<amount>", and tenure "<tenure>"
    Then FD creation limit status should be "<expectedResult>"

    Examples:
      | accountType | amount | tenure                           | expectedResult |
      | current     | 1000   | 12 months (7% p.a.) - 1 year    | enforced       |