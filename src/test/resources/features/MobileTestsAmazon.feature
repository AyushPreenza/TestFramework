Feature: Amazon Search and Add to Cart

  @MobileTest4
  Scenario Outline: 34521_Searching a product
    Given open the Amazon app on "<DeviceDetails>"
    And click on skip sign in
    And search for product
    And add the item to amazoncart
    Then verify item is added to cart or not
    Examples:
      | DeviceDetails |
      | emulator-5554_11 |
     #| Google Pixel 3_9.0 |
     #|emulator-5554_10|
	 #|emulator-5554_9|
