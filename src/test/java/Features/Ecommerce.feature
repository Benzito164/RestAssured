@a
Feature: Testing the ecommerce feature of adding a product and
  deleting it.

  @ecom
  Scenario: End to end test of the ecommerce api
    Given i login successfully
    And i create a new product
    When i add the created product to my basket
    And i delete the created order
    Then i get a "Product Deleted Successfully" message