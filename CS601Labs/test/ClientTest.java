import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

import cs601.webclient.YelpCrawler;
import cs601.yelpapp.data.YelpStore;


public class ClientTest {

	@Test
	public void testFetchBusinesses() {
		
		fetchBusinesses("input/business_ids.txt", "testFetchBusineses");

	}

	@Test
	public void testFetchBusinessesBadId() {
		
		fetchBusinesses("input/business_ids_bad.txt", "testFetchBusinesesBadId");
	}

	@Test
	public void testScrapeBusinesses() {
		
		scrapeBusinesses("input/business_ids.txt", "testScrapeBusineses");

	}

	@Test
	public void testScrapeBusinessesBadId() {
		
		scrapeBusinesses("input/business_ids_bad.txt", "testScrapeBusinesesBadId");
	}

	private void fetchBusinesses(String busIds, String testName) {
		
		YelpStore store = new YelpStore();
		store = YelpCrawler.fetchBusinesses(busIds, "input/keys.json", store);
		
		Path expected = FileSystems.getDefault().getPath(TestUtils.OUTPUT_DIR + File.separator + "crawloutput.txt");
		Path actual = FileSystems.getDefault().getPath(TestUtils.RESULT_DIR + File.separator + "crawloutput.txt");
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

	private void scrapeBusinesses(String busIds, String testName) {
		
		YelpStore store = new YelpStore();
		store = YelpCrawler.scrapeBusinesses(busIds, store);
		
		Path expected = FileSystems.getDefault().getPath(TestUtils.OUTPUT_DIR + File.separator + "scrapeoutput.txt");
		Path actual = FileSystems.getDefault().getPath(TestUtils.RESULT_DIR + File.separator + "scrapeoutput.txt");
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
