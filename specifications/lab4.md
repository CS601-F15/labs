Lab 4 - Web Clients
==========================

The goal of this lab is to provide experience building a web client.

For this lab, you will implement a Yelp web client in two ways: (1) using Yelp's web service API and (2) using a screen scraper.

Your client will process a text file containing several Yelp business IDs. For each ID, it will retrieve the relevant business data from the main Yelp website and add the business to the YelpStore provided as input. It will return the resulting YelpStore.

Part of the goal of this lab is to give you an opportunity to compare and contrast a web service with the *human-readable* web. As you implement each client, think about the advantages and disadvantages of the approach.


##Requirements##

You will implement two methods of the class `cs601.webclient.YelpCrawler`. `fetchBusinesses` will use Yelp's web service API to retrieve the relevant business data. `scrapeBusinesses` requires you to implement a screen scraper to retrieve the same information.

In both cases, the information you will need to retrieve is the following:

- Business ID
- Name
- City
- State
- Latitude
- Longitude
- Neighborhoods (list)

Both methods take as input a String representing the name of a file that contains a list of Yelp business IDs, one per line, and a YelpStore that may or may not already contain data. 

Both methods return the YelpStore passed as input with the addition of information about all of the businesses listed in the file.

###Web Service Client###
The web service client will use Yelp's [API 2.0](https://www.yelp.com/developers/documentation/v2/overview).

First, you must register as a developer on Yelp.

Because you will need to authenticate using [OAuth](http://oauth.net/2/), you may use the [Yelp Java library](https://github.com/Yelp/yelp-api/tree/master/v2/java) for this portion of the assignment.

The `fetchBusinesses` method will take as a parameter a String representing a file containing all necessary keys to use the API. The format of the file is expected to be as follows:

```
{
 "yelpconsumerkey": "<key>",
 "yelpconsumersecret": "<key>",
 "yelptoken": "<key>",
 "yelptokensecret": "<key>",
}
```

Make sure to closely read the [documentation](https://www.yelp.com/developers/documentation/v2/overview) to determine how to retrieve the necessary information.


###Screen Scraper###

The screen scraper will use a raw Java socket to connect to the Yelp web server, download the appropriate page, and extract the required business information from the HTML.

The requirements for this portion of the assignment are as follows:

1. You must use a raw socket and compose the HTTP request manually.
2. You may not use the Java URL class, or any other external libraries.
3. It is strongly recommended you use regular expressions to identify the appropriate data in the HTML page.

##Submission##
All tests in Lab4Test must pass before submission.

Follow these instructions *carefully* in order to submit your lab: [Lab Guidelines](https://github.com/CS601-F15/lectures/blob/master/Notes/labguidelines.md).