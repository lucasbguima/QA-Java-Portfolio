Feature: Product Management Web UI
  As a user
  I want to manage products via the web interface
  So that I can keep the inventory updated

  Scenario: Successfully create a new product via UI
    Given I am on the product management page
    When I fill in the product form with name "Mechanical Keyboard", price "150.00", description "RGB Switch Blue" and image "https://via.placeholder.com/150"
    And I click the submit button
    Then the product "Mechanical Keyboard" should be visible in the product list

  Scenario: Fail to create a product without a name
    Given I am on the product management page
    When I fill in the product form with price "100.0" and description "No Name Product"
    And I click the submit button
    Then an error message "Name is required" should be displayed