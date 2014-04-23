Meta:

@issue URL-123

Narrative:
In order to know how successful my link is
As a website admin
I want to know how many people have clicked on my shortened URL

Scenario: Fetch number of clicks
Given I have registered a shortened URL for http://www.google.com
And it has been clicked 10 times
When I query usage statistics for http://www.google.com
Then I should get 10 clicks
And the URL status should be ACTIVE