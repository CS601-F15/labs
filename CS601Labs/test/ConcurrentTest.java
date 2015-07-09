import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import cs601.concurrent.ReentrantLock;
import cs601.concurrent.WorkQueue;
import cs601.yelpapp.data.YelpStore;
import cs601.yelpapp.data.utils.StoreBuilder;


public class ConcurrentTest {

	@Test
	public void testLockSimple() {		
		String testName = "testLockSimple";
		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		lock.lockRead();

		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Read lock not held. %n", testName), lock.hasRead());
		lock.unlockRead();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Read lock not released. %n", testName), lock.hasRead());
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());
		lock.unlockWrite();
		Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
				" Write lock not released. %n", testName), lock.hasWrite());
	}

	@Test
	public void testLockMultipleWrites() {		
		String testName = "testLockMultipleWrites";
		ReentrantLock lock = new ReentrantLock();
		lock.lockWrite();
		lock.lockWrite();

		lock.unlockWrite();
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Write lock not held. %n", testName), lock.hasWrite());
		lock.unlockWrite();
	}

	@Test
	public void testWriteLockMultiThread() {	
		String testName = "testWriteLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		boolean result = lock.tryLockWrite();
		if(!result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Unable to acquire write lock. %n", testName));
		}
		Thread t1 = new Thread() {
			public void run() {
				Assert.assertFalse(String.format("%n" + "Test Case: %s%n" +
						" Read lock acquired. %n", testName), lock.tryLockRead());
			}
		};

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		lock.unlockWrite();
	}

	@Test
	public void testReadLockMultiThread() {	
		String testName = "testReadLockMultiThread";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();

		Thread t1 = new Thread() {
			public void run() {
				Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
						" Read lock not acquired. %n", testName), lock.tryLockRead());
			}
		};

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			Assert.fail();
		}
		lock.unlockRead();
	}

	@Test
	public void testLockUpgrade() {
		String testName = "testLockUpgrade";

		ReentrantLock lock = new ReentrantLock();
		lock.lockRead();

		boolean result = lock.tryLockWrite();
		if(result) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Lock upgrade read to write should be disallowed. %n", testName));
		}
		lock.unlockRead();

	}

	@Test
	public void testWorkQueue() {

		String testName = "testWorkQueue";
		WorkQueue queue = new WorkQueue(3);

		final AtomicInteger count = new AtomicInteger();
		Runnable r = new Runnable() {
			public void run() {
				count.incrementAndGet();
			}
		};
		int runs = 5;
		for(int i = 0; i < runs; i++) {
			queue.execute(r);
		}
		queue.shutdown();
		queue.awaitTermination();
		Assert.assertTrue(String.format("%n" + "Test Case: %s%n" +
				" Invalid execution of WorkQueue. %n", testName), count.intValue() == runs);
	}

	@Test	
	public void testConcurrentBuild() {
		String testName = "testConcurrentBuild";

		YelpStore store = StoreBuilder.concurrentBuild(TestUtils.INPUT_DIR + File.separator + "datasets");
		Path expected = FileSystems.getDefault().getPath(TestUtils.OUTPUT_DIR + File.separator + "yelpstore.txt");
		Path actual = FileSystems.getDefault().getPath(TestUtils.RESULT_DIR + File.separator + "yelpstore.txt");
		try {
			Files.deleteIfExists(actual);
		} catch (IOException e1) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Error deleting file: %s%n", testName, e1.getMessage()));
		}
		store.printToFile(actual);

		int count = 0;
		try {
			count = TestUtils.checkFiles(expected, actual);
		} catch (IOException e) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" File check failed: %s%n", testName, e.getMessage()));
		}

		if (count <= 0) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Mismatched Line: %d%n", testName, -count));
		}
	}
}
