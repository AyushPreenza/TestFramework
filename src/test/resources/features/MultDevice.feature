Feature: GoogleSearch Page test for multiple devices

  @MDevices11
  Scenario: Launch the amazon apk and test the features
    Given Launch the amazon apk file
    And click on SKIP SIGN IN button
    When select for an item by category in the application
    And add the item to cart on the screen
    Then verify item is added to cart on screen