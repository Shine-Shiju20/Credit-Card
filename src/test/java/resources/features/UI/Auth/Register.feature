Feature: Register Functionality

  Background:
    Given User launches banking application
    And User clicks on Home Register button


# ------------------------------------------------
# Email Validations
# ------------------------------------------------

  @validEmailRegistration
  Scenario Outline: Verify valid email registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then User should navigate to KYC page

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 |
      | PRAJWALPRAJWAL222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 |


  @invalidEmailValidation
  Scenario Outline: Verify invalid email validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then Proper registration email validation message should be displayed as "<expectedResult>"

    Examples:

      | email                           | phone      | password     | confirmPassword | pin  | expectedResult |
      | PRA JWA LPRA JWAL222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 |  |
      | prajwalprajwal222gmail.com     | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Please enter a valid email |
      | prajwalprajwal222@             | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Please enter a valid email |


  @duplicateEmail
  Scenario Outline: Verify duplicate email validation

  When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

  And User clicks on Next button

  And User enters KYC details with "<fullname>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

  And User submits KYC registration form

  Then Duplicate user validation message should be displayed as "<expectedResult>"

   Examples:

  | email                         | phone      | password     | confirmPassword | pin  | fullname    | dob        | gender | address                     | aadhaar      | pan        | income | occupation         | expectedResult |
  | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram P B | 10-06-2000 | Male   | Bengaluru Karnataka India | 908443672897 | BANFE1218A | 600000 | Software Engineer | User with provided email, phone, Aadhaar, or PAN already exists. |

  @emptyEmail
  Scenario Outline: Verify empty email validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then Required field validation message should be displayed as "<expectedResult>"

    Examples:

      | email | phone      | password     | confirmPassword | pin  | expectedResult |
      |       | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | All fields are required |


# ------------------------------------------------
# Phone Number Validations
# ------------------------------------------------

  @validPhone
  Scenario Outline: Verify valid phone number registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then User should navigate to KYC page

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 |


  @invalidPhoneValidation
  Scenario Outline: Verify invalid phone validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then invalid phone validation should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 96B211AV04 | Prajwal@2075 | Prajwal@2075    | 8999 | Enter a valid 10-digit phone number |
      | prajwalprajwal222@gmail.com | 96321111   | Prajwal@2075 | Prajwal@2075    | 8999 | Enter a valid 10-digit phone number |


  @phoneRestriction
  Scenario Outline: Verify phone field restriction

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    Then phone field should restrict value as "<expectedResult>"

    Examples:

      | email                         | phone         | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104646 | Prajwal@2075 | Prajwal@2075    | 8999 | 9632111104 |


# ------------------------------------------------
# Password Validations
# ------------------------------------------------

  @invalidPassword
  Scenario Outline: Verify invalid password validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then Registration password validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | prajwal@2075 | prajwal@2075    | 8999 | Password: 8+ chars with uppercase, lowercase, number & special character |
      | prajwalprajwal222@gmail.com | 9632111104 | PRAJWAL@2075 | PRAJWAL@2075    | 8999 | Password: 8+ chars with uppercase, lowercase, number & special character |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@     | Prajwal@        | 8999 | Password: 8+ chars with uppercase, lowercase, number & special character |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal2075  | Prajwal2075     | 8999 | Password: 8+ chars with uppercase, lowercase, number & special character |


  @passwordMismatch
  Scenario Outline: Verify password mismatch validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then Registration password mismatch validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal2@075 | Prajwal2@076    | 8999 | Passwords do not match |


# ------------------------------------------------
# Confirm Password Validations
# ------------------------------------------------

  @emptyConfirmPassword
  Scenario Outline: Verify empty confirm password validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then Confirm password required validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 |                 | 8999 | All fields are required |


# ------------------------------------------------
# Transaction PIN Validations
# ------------------------------------------------

  @invalidTransactionPinValidation
  Scenario Outline: Verify invalid transaction PIN validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    Then invalid PIN validation should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 899  | Transaction PIN must be 4-6 digits |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 1234 | PIN is too weak. Please choose a more secure PIN. |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 12BD | Transaction PIN must be 4-6 digits |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 89@# | Transaction PIN must be 4-6 digits |


  @pinRestriction
  Scenario Outline: Verify PIN field restriction

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    Then PIN field should restrict value as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin      | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 89996565 | 899965 |




# ------------------------------------------------
# Full Name Validations
# ------------------------------------------------

  @invalidFullNameNumeric
  Scenario Outline: Verify Full Name does not accept numerics

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Full Name validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhi123  | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 600000   | Developer  | Full name must contain only alphabets |


  @invalidFullNameSpecialCharacters
  Scenario Outline: Verify Full Name does not accept special characters

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Full Name validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName  | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhi@ram! | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 600000   | Developer  | Full name must contain only alphabets |


  @fullNameMinLength
  Scenario Outline: Verify Full Name minimum 3 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Full Name validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Ab       | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 60200   | Developer  |Full name must be at least 3 characters |


  @fullNameMaxLength
  Scenario Outline: Verify Full Name maximum 50 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Full Name validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName                                             | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                           |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | AbhiramPrabhakarVeryLongNameTestingMaximumLimitCheck | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1218A | 60200   | Developer  | Full name cannot exceed 50 characters |


# ------------------------------------------------
# Address Validations
# ------------------------------------------------

  @addressMinLength
  Scenario Outline: Verify Address minimum 10 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Address validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address   | aadhaar      | pan        | income | occupation | expectedResult                                 |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-06-2000 | Male   | Bengalu | 333334444333 | BANFE121A | 60200   | Developer  | Address must be at least 10 characters long |


  @addressMaxLength
  Scenario Outline: Verify Address maximum 250 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Address validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                                                                                                                                                                                                                                                           | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-06-2000 | Male   | Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in reprehenderit | 333334444333 | BANFE121A | 60200   | Developer  | Address cannot exceed 250 characters |


# ------------------------------------------------
# Occupation Validations
# ------------------------------------------------

  @invalidOccupation
  Scenario Outline: Verify Occupation accepts only alphabets

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Occupation validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                           |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 60200   | Dev123@    | Occupation must contain only alphabets |


  @occupationMinLength
  Scenario Outline: Verify Occupation minimum 2 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Occupation validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                           |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 60200   | A          | Occupation must be at least 2 characters |


  @occupationMaxLength
  Scenario Outline: Verify Occupation maximum 50 characters validation

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Occupation validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation                                                   | expectedResult                           |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-06-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE121A | 60200   | SeniorSoftwareDevelopmentEngineeringManagerLeadRoleTesting | Occupation cannot exceed 50 characters |


  # ------------------------------------------------
# DOB Validations
# ------------------------------------------------

  @validDOB
  Scenario Outline: Verify valid Date of Birth during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 07/03/2001 | Male   | Bengaluru Karnataka India | 335338762333 | BANTH2121A | 500000 | Developer |


  @futureDOBValidation
  Scenario Outline: Verify future Date of Birth validation during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then DOB validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2030 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | 500000 | Developer | You must be at least 18 years old |


  @emptyDOB
  Scenario Outline: Verify null Date of Birth validation during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then DOB validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult              |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  |     | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | 500000 | Developer | All fields are required |


  @underAgeValidation
  Scenario Outline: Verify underage Date of Birth validation during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then DOB validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                       |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2012 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | 500000 | Developer | You must be at least 18 years old |


  @invalidDOBFormat
  Scenario Outline: Verify invalid Date of Birth format rejection during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then DOB validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult     |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 30/06/2004 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | 500000 | Developer | You must be at least 18 years old |


# ------------------------------------------------
# Gender Validations
# ------------------------------------------------

  @validMaleGender
  Scenario Outline: Verify Male gender selection during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Male   | Bengaluru Karnataka India | 335338762333 | BANTH2121A | 500000 | Developer |


  @validFemaleGender
  Scenario Outline: Verify Female gender selection during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Female | Bengaluru Karnataka India | 335338762333 | BANTH2121A | 500000 | Developer |


  @validOtherGender
  Scenario Outline: Verify Other gender selection during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Other  | Bengaluru Karnataka India | 335338762333 | BANTH2121A | 500000 | Developer |


# ------------------------------------------------
# Income Validations
# ------------------------------------------------

  @validIncome
  Scenario Outline: Verify valid annual income during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Male   | Bengaluru Karnataka India | 335338762333 | BANTH2121A | 55555555 | Developer |


  @invalidIncome
  Scenario Outline: Verify annual income below 10000 validation during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then income validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | 5000   | Developer | Annual income cannot be below 10,000 |


  @emptyIncome
  Scenario Outline: Verify null annual income validation during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then income validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult              |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A |        | Developer | All fields are required |


  @negativeIncome
  Scenario Outline: Verify negative annual income value rejection during registration

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then income validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address                    | aadhaar      | pan        | income | occupation | expectedResult                          |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 10-03-2000 | Male   | Bengaluru Karnataka India | 333334444333 | BANFE1221A | -25000 | Developer | Annual income cannot be below 10,000 |

  # ------------------------------------------------
# PAN Validations
# ------------------------------------------------

  @validPAN
  Scenario Outline: Validate valid PAN

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar        | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 335338762333 | BANTH2121A | 601300   | Developer |


  @panLowerCaseRestriction
  Scenario Outline: Validate lowercase PAN conversion

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    Then PAN field should restrict value as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar        | pan        | income | occupation | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 3333-3444-4335 | banfe1221a | 601400   | Developer | BANFE1221A |


  @invalidPANFormat
  Scenario Outline: Validate invalid PAN format

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then PAN validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar        | pan        | income | occupation | expectedResult                    |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 3333-3444-4335 | BANFEAA21A | 601500   | Developer | Enter valid PAN (e.g., ABCDE1234F) |


  @panSpecialCharacterRestriction
  Scenario Outline: Validate PAN special characters restriction

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    Then PAN field should restrict value as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar        | pan        | income | occupation | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 3333-3444-4335 | BANFE12@1A | 601600   | Developer | BANFE121A |


# ------------------------------------------------
# Aadhaar Validations
# ------------------------------------------------

  @validAadhaar
  Scenario Outline: Validate valid Aadhaar

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then User registration should proceed successfully

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar        | pan        | income | occupation |
      | dharwadguddu123@gmail.com | 9631117704 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 335338762333 | BANTH2121A | 601700   | Developer |


  @invalidAadhaarLength
  Scenario Outline: Validate Aadhaar less than 12 digits

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    And User submits KYC registration form

    Then Aadhaar validation message should be displayed as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar       | pan        | income | occupation | expectedResult                    |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 3333-3444-43 | BANFE121A | 601800   | Developer | Enter valid 12-digit Aadhaar number |


  @aadhaarAlphabetRestriction
  Scenario Outline: Validate Aadhaar with alphabets restriction

    When User enters registration details with "<email>" "<phone>" "<password>" "<confirmPassword>" "<pin>"

    And User clicks on Next button

    And User enters KYC details with "<fullName>" "<dob>" "<gender>" "<address>" "<aadhaar>" "<pan>" "<income>" "<occupation>"

    Then Aadhaar field should restrict value as "<expectedResult>"

    Examples:

      | email                         | phone      | password     | confirmPassword | pin  | fullName | dob        | gender | address               | aadhaar         | pan        | income | occupation | expectedResult |
      | prajwalprajwal222@gmail.com | 9632111104 | Prajwal@2075 | Prajwal@2075    | 8999 | Abhiram  | 15-06-2004 | Male   | Bengaluru Karnataka | 3333-3444-43A5 | BANFE121A | 601900   | Developer | 3333-3444-435 |