@register_station
Feature: Registering a weather station

  @negative
  Scenario Outline: Unsuccessful Registration due to invalid API ID
    Given I like to register a weather station
      And with "external_id" as "<external_id>"
      And with "name" as "<name>"
      And with "latitude" as "<latitude>"
      And with "longitude" as "<longitude>"
      And with "altitude" as "<altitude>"
    When I post the payload with "<INVALID>" API ID
    Then I get "code" as "<code>"
      And I get "message" as "<message>"
      Examples:
          |   external_id     |   name                         |   latitude |   longitude  |   altitude  |   code  |   message                                                                           |
          |   DEMO_TEST001    |   Team Demo Test Station 001   |   33.33    |   -122.43    |   222       |   401   |   Invalid API key. Please see http://openweathermap.org/faq#error401 for more info. |

  @positive
  Scenario Outline: Successful Registration due to Valid API ID
    Given I like to register a weather station
      And with "external_id" as "<external_id>"
      And with "name" as "<name>"
      And with "latitude" as "<latitude>"
      And with "longitude" as "<longitude>"
      And with "altitude" as "<altitude>"
    When I post the payload with "<VALID>" API ID
    Then I get "code" as "<code>"
      And I get "external_id" as "<external_id>"
      And I get "name" as "<name>"
      And I get "latitude" as "<latitude>"
      And I get "longitude" as "<longitude>"
      And I get "altitude" as "<altitude>"
    Examples:
      |   external_id     |   name                         |   latitude |   longitude  |   altitude  |   code  |
      |   DEMO_TEST001    |   Team Demo Test Station 001   |   33.33    |   -122.43    |   222       |   201   |
      |   DEMO_TEST002    |   Team Demo Test Station 002   |   44.44    |   -122.44    |   111       |   201   |