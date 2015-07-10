Lab 1 - Basic YelpStore
==========================

The goal of this laboratory assignment is to familiarize you with the following:

- [Data structures in Java 8](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/package-summary.html)
- [Github](https://github.com/)
- The [development](https://github.com/CS601-F15/lectures/blob/master/Notes/environment.md) and [lab submission](https://github.com/CS601-F15/lectures/blob/master/Notes/labguidelines.md) guidelines for this course

For this lab, you will design and implement a data structure to store the data provided in the [Yelp Academic Dataset](https://www.yelp.com/academic_dataset). The Yelp dataset contains information about businesses, information about users, and reviews, by users, of businesses in the dataset.

If you are new to San Francisco, I recommend you begin by creating a [Yelp](https://www.yelp.com/) account and exploring the web site!

##Getting Started##
Begin by setting up your Java environment. You will find suggested guidelines here: [Environment setup](https://github.com/CS601-F15/lectures/blob/master/Notes/environment.md). 

This document describes my environment, which includes Eclipse and the Github tool. You are not required to use either of these. You may, for example, use IntelliJ as your IDE and command line git for accessing your github account. Keep in mind, however, that I will provide limited support for tools other than the ones I suggest.

Also keep in mind that I will expect the structure of your submission to be exactly as specified. You must use exactly the directory structure I provide or you will be required to revise and resubmit.

##CS601Labs Project##
The CS601Labs project contains skeleton code and test suites for your first several assignments. For a given lab, you only need to implement the code to pass the tests for that assignment. For example, you will not need to implement the APIs in the `cs601.concurrent` package until Lab 3.


##Requirements##

For Lab 1, you are required to implement a *subset* of the methods in the class `cs601.yelpapp.data.YelpStore`. You must implement a default constructor, and the methods `addUser`, `addReview`, `addBusiness`, `toString`, and `printToFile`. 

:warning: *You may not make any modifications to the API or the test cases provided.*

You do *not* need to implement the get methods of `YelpStore` for Lab 1.

The Javadoc provided in the skeleton code provides specific descriptions of the expected behavior and output of each method.

The key element of this lab is the design of a data structure that will store all of the user information, business information, and reviews. Each review is of a specific business and authored by a specific user. The YelpStore will store all information, but may use multiple data structures for this purpose.

Think carefully about **efficiency**. Some specific considerations include avoiding storing duplicate data where possible and designing your data structure to optimize access to the data. Though you do not need to implement the get methods, look carefully at how the data will be accessed ensure efficiency.

##Hints##
1. Make sure to follow good coding practices, including adhering to the suggested [style guidelines](https://github.com/CS601-F15/lectures/blob/master/Notes/style.md).
2. You may create additional supporting classes. My solution uses five additional classes. 
3. It is highly recommended that you design your own test cases; do not just rely on the test cases provided for you.


##Submission##
Follow these instructions *carefully* in order to submit your lab: [Lab Guidelines](https://github.com/CS601-F15/lectures/blob/master/Notes/labguidelines.md).