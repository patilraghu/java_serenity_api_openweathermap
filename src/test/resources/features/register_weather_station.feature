@register_station
Feature: Open Weather Map feature testing

  @negative
  Scenario Outline: Registration without API key
    Given "external_id" as "<external_id>"
      And "name" as "<name>"
      And "latitude" as "<latitude>"
      And "longitude" as "<longitude>"
      And "altitude" as "<altitude>"
    When Payload posted without API key
    Then "code" is "<code>"
      And "message" is "<message>"
      Examples:
          |   external_id     |   name                         |   latitude|   longitude  |   altitude  |   code  |   message                                                                           |
          |   DEMO_TEST001    |   Team Demo Test Station 001   |   33.33   |   -122.43    |   222       |   401   |   Invalid API key. Please see http://openweathermap.org/faq#error401 for more info. |