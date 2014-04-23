Narrative:
In order to fit long URLs into short text messages
As an internet user
I want to be able to represent URLs in a shortened form

Scenario: Shorten URLs
Given a url <providedUrl>
When I request the shortened form of this url
Then the shortened form should be <expectedUrl>
Examples:
| providedUrl             | expectedUrl        |
| http://www.google.com/  | http://goo.gl/fbsS |
| http://www.amazon.com/  | http://goo.gl/xj57 |

Scenario: Shorten Urls (technical version)
Given a url http://www.google.com/
When I request the shortened form of this url
Then I should obtain the following JSON message:
 {
  "kind": "urlshortener#url",
  "id": "http://goo.gl/fbsS",
  "longUrl": "http://www.google.com/"
 }

