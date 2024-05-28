Feature: Amazon Search & Add Product To Cart

  @Mparallel
  Scenario: Open the amazon apk;search & Add product to cart
    Given Open the amazon apk file
    And click on SKIP SIGN in button
    When select item by category in the application 
    And add selected item to cart on the screen
    Then verify selected item is added to cart on screen

  @Mobile
  Scenario: Verify application is able to install and launch in the emulator.
    Given Open the application
    
   