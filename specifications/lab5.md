Lab 5 - Web Server
==========================

The goal of this lab is provide experience building an HTTP server using raw sockets. 

For this lab, you will implement a modular and application-independent HTTP server along with a RESTful API for exposing your YelpStore `get*` methods.

:warning: **The submission procedure for this lab is different from previous labs. Please read the submission instructions carefully.**


##Requirements##

1. You must use raw Sockets for this assignment. 
2. You may *not* use any libraries for parsing HTTP messages. This includes the start-line, the headers, and the body of the message. 
3. You only need to support HTTP GET requests.
4. Your basic server must be application independent. None of the code used to parse HTTP messages, etc, should be specific to the YelpStore application.
5. When your server starts, it will build the YelpStore using the `StoreBuilder` class to read in *all* of the data in the input directory as in [Lab 3](lab3.md). My test cases will verify the data returned by your server against the expected YelpStore contents.

##Server Behavior##

###HTTP Methods###

`GET` is the only HTTP method your server will support. If a client request specifies any other method (e.g., POST, PUT, DELETE) return a `405 - Method Not Allowed`.

###APIs###
Only the following APIs will be supported. The response to a request for any other API endpoint will be `404 - Not Found`.


**`/rating`**

- Example: `http://localhost:1024/rating?businessID=4lkx2UdISL1-2hfcTYI0lg`
- Must have a required parameter of `businessID`. 
  - If the `businessID` is missing, return `404 - Not Found`.
- If the `businessID` provided refers to a valid business the response will be a JSON document as follows. `success` must be `true`, the `rating` will be the value retrieved from your YelpStore, and the `businessID` is the value the server received as a parameter.

```json
{
	"success":true,
	"rating":2.75,
	"businessID":"4lkx2UdISL1-2hfcTYI0lg"
}
```
- If the `businessID` does not refer to a business that exists in the YelpStore, the response will be as follows, where `success` and `businessID` have the same semantic meaning described above.

```json
{
	"success":false,
	"businessID":"invalid_bus_id_provided_by_user"
}

```

**`/businesses`**

- Example: `http://localhost:1024/businesses`
- No parameters are required. 
- Returns a JSON object containing a list of IDs of all businesses in the YelpStore with format as follows:

```json
{
	"success":true,
	"businesses":["--3iH7ezDwTGn5lolFMdhg","--q0WbTS_Gl55W3bC4zmSA"]
}
```

**`/reviewers`**

- Example: `http://localhost:1024/reviewers?businessID=4lkx2UdISL1-2hfcTYI0lg`
- Must have a required parameter of `businessID`. 
  - If the `businessID` is missing, return `404 - Not Found`.
- If the `businessID` provided refers to a valid business the response will be a JSON document as follows. `success` must be true, the `users` array will be the value retrieved from your YelpStore, and the `businessID` is the value the server received as a parameter.

```json
{
	"success":true,
	"businessID":"4lkx2UdISL1-2hfcTYI0lg",
	"users":["Kandace P.","mrs y.","H C.","Alana S.","Stephanie A.","Kristen G.","Miria M.","Christine Z."]
}
```

- If the `businessID` does not refer to a business that exists in the YelpStore, the response will be as follows, where `success` and `businessID` have the same semantic meaning described above.

```json
{
	"success":false,
	"businessID":"invalid_bus_id_provided_by_user"
}
```

##Resources##
1. [HTTP Request Specification](http://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html)
2. [HTTP Response Specification](http://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html)


##Submission##
This lab may be resubmitted until October 23, 2015. Solutions that are not fully functional by October 23 *may* receive some partial credit. This applies to Lab 5 only.

There are two steps for submitting this lab:

1. Commit your code to Github and create a new release as in previous labs.
2. Launch your server on your assigned [microcloud node and port](mcassigns.md). For example, andyai1992 will make sure his server is running on node mc01 and is listening at port 2000.
3. Make sure your server will continue running, even after you log out of the node! I recommend you learn a bit about [nohup](http://linux.101hacks.com/unix/nohup-command/).

You are expected to implement your own test suite for this assignment. You may use curl and/or a web browser for initial testing. 

The following python test suite implements many of the tests I will use to verify your solution: [http\_server\_test.py](https://github.com/CS601-F15/labs/blob/master/CS601Labs/test/http_server_test.py) It is not required that you use this test suite during development, however if your code does not pass at least these tests it will receive a deduction. I will use additional tests when verifying your code.