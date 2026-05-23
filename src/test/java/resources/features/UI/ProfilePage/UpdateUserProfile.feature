Feature: Update User Profile Functionality

  Background:
    Given User launches the banking application
    And User logs in using valid credentials
    And User navigates to the Profile page

  @UpdateProfile @TC_4
  Scenario: Verify if user can update profile details successfully
    When User updates profile details
    Then User should see message "Profile updated successfully"
    And Details to be Displayed Correctly After updation

  @ProfileValidation @CancelProfileValidation @TC_24
  Scenario: Verify user can cancel profile edit operation
    When User clicks Edit Profile button
    And User edits profile details
    And User clicks Cancel Profile button
    Then Profile data should revert back to previous data

  @ProfileValidation @profileDataPersistence @TC_25
  Scenario: Verify updated profile data persists after relogin
    When User clicks Edit Profile button
    And User edits profile details
    And User clicks Save Profile button
    And User logs out from application
    And User relogins with valid credentials
    Then Saved profile data should persist after relogin