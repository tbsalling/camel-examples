Exercise
========

In this exercise we want to create a Camel-based application which can 
connect to Twitter and receive a stream of tweets.

Getting Twitter ready
---------------------
1. Create a new Twitter account, if you don't have one.
1. Prepare your Twitter account to be used by your own application.
    1. Go to http://apps.twitter.com and click "Create new app".
    2. Fill in the lot and accept the developer agreement.
    3. Go to the "Keys and access tokens" tab
    3. Generate and take note of your personal (and secret!)
        - Consumer key (API Key)
        - Consumer secret (API Secret)
        - Access Token
        - Access Token Secret

No. 1 - Create a Twitter application
----
1. Create a new "empty" Camel application 
1. Based on the camel-twitter component - create a route which
   1. Connects to Twitter
   2. Streams tweets to the "stream:out" endpoint
   
No. 2 - Filter the tweets
----
If you used "Camel" as keyword for picking Tweets - then try
to filter away sleazy tweets containing the sequence "Camel toe".



   
   
   