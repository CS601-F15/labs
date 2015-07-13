Lab 3 - Concurrency
==========================

The goal of this lab is to provide experience with concurrency and multi-threaded programming in Java.

For this lab, you will implement the following:

1. A `cs601.concurrent` library that provides both a *reentrant* read-write lock and a work queue.
2. A thread-safe version of your `YelpStore`.
3. A process for *concurrently* building a new `YelpStore`.

You may **not** use any of the classes in the package `java.util.concurrent` or `java.util.concurrent.locks`.

##Requirements##

###Reentrant Read/Write Lock
You will implement a *reentrant* read-write lock as defined in `cs601.concurrent.ReentrantLock`.

The semantics of this lock are very simiar to Java's [ReentrantReadWriteLock](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.html), however you may not use this class in your final submission!

Make sure to adhere to the following rules:

- Multiple threads may hold the read lock as long as no thread holds the write lock.
- Only one thread may hold the write lock at any time.
- A thread holding a write lock may also acquire the read lock. 
- A thread only releases one lock at a time, therefore a thread may acquire a write lock then acquire a read lock. If this thread releases the write lock it will still hold the read lock.
- A thread holding a read lock *may not* upgrade to the write lock. This is to prevent deadlock.
- The `try...Lock` methods will *not* block, but rather return true or false to indicate whether the lock was acquired.
- The `lock` methods will block until the lock is acquired. Hint, you should use the `wait` method.

###WorkQueue
You will implement a work queue as defined in `cs601.concurrent.WorkQueue`.

You may reuse the code here: [IBM WorkQueue Implementation](http://www.ibm.com/developerworks/library/j-jtp0730/). Note, copying code from the web is *only* allowed in this specific case!

You will need to both modify and extend the IBM WorkQueue code. You must implement the following two methods.

- `shutdown` - This method is called to prevent the work queue from accepting any new work. This method is *non-blocking* and will return even if there is still (old) work in the queue.
- `awaitTermination` - This is a *blocking* method that will return only once all work in the queue is complete.

###Thread-safe YelpStore

You will modify your `cs601.yelpapp.data.YelpStore` class to provide thread-safe access to the data.

Your implementation must allow concurrent reads of the data, but disallow both concurrent writes and concurrent read/write of the data.

###StoreBuilder

You will implement a class `cs601.yelpapp.data.utils.StoreBuilder` that has a static method to recursively traverse a given directory and concurrently process all JSON files found, adding their contents to the `YelpStore`.

Each JSON file found will be *processed* by a separate thread - that is, a new `Runnable` job that will process that single JSON file will be added to the work queue.

The JSON is expected to be formatted as described here: [Yelp Academic Dataset](https://www.yelp.com/academic_dataset). Make sure to handle error cases, however!

##Hints and Restrictions##
1. You will need to write your own test cases, particularly for the `YelpStore`. The test cases provided provide only minimal correctness checks. Additional tests may be run by the instructor before you qualify for code review. 
2. Consider testing your thread-safe YelpStore and StoreBuilder by using Java's lock and thread pool implementation. Though you  may not use these in your final submission, they can be useful for testing along the way.
3. Do not delay in getting started on this assignment. It may take longer than you think!

##Submission##
All tests in Lab3Test must pass before submission.

Follow these instructions *carefully* in order to submit your lab: [Lab Guidelines](https://github.com/CS601-F15/lectures/blob/master/Notes/labguidelines.md).