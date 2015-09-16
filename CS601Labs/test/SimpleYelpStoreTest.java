
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import cs601.yelpapp.data.YelpStore;

public class SimpleYelpStoreTest {
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testSimpleAddReview() {
		String testName = "testSimpleAddReview";
		YelpStore store = new YelpStore();
		
		store.addReview("bus-id", 2, "Bad review", "2011-11-11", "user-id");		
		String expected = "";
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testSimpleAddBusiness() {
		String testName = "testSimpleAddBusiness";
		YelpStore store = new YelpStore();
		
		store.addBusiness("bus-id", "Bus Name", "Austin", "TX", 12.345, 98.765);
		String expected = "Bus Name - Austin, TX (12.345, 98.765) ()";
		
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testSimpleAddUser() {
		String testName = "testSimpleAddUser";
		YelpStore store = new YelpStore();
		
		store.addUser("user-id", "Bob1");
		String expected = "";
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testSimpleAdd() {
		String testName = "testSimpleAdd";
		YelpStore store = new YelpStore();
		
		store.addUser("user-id", "Bob1");
		store.addReview("bus-id", 2, "Bad review", "2011-11-11", "user-id");		
		store.addBusiness("bus-id", "Bus Name", "Austin", "TX",  12.345, 98.765);

		String expected = "Bus Name - Austin, TX (12.345, 98.765) ()\n" +
						"2 - Bob1: Bad review";
		
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testAddMissingUser() {
		String testName = "testAddMissingUser";
		YelpStore store = new YelpStore();
		
		store.addUser("user-id", "Bob1");
		store.addReview("bus-id", 2, "Bad review", "2011-11-11", "user-id");		
		store.addReview("bus-id", 5, "Good review", "2011-11-10", "user-id2");		
		store.addBusiness("bus-id", "Bus Name", "Austin", "TX",  12.345, 98.765);

		String expected = "Bus Name - Austin, TX (12.345, 98.765) ()\n" + 
					"5 - : Good review\n" + 
					"2 - Bob1: Bad review";

		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}

	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testAddMissingBusiness() {
		String testName = "testAddMissingBusiness";
		YelpStore store = new YelpStore();
		
		store.addUser("userId", "Bob1");
		store.addReview("bus-id", 2, "Bad review", "2011-11-11", "user-id");		

		String expected = "";
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testTwoBusinessesSameName() {
		String testName = "testTwoBusinessesSameName";
		YelpStore store = new YelpStore();
		
		store.addUser("user-id", "Bob1");
		
		store.addBusiness("bus-id1", "Bus Name", "Austin", "TX",  12.345, 98.765);
		store.addBusiness("bus-id2", "Bus Name", "Portland", "OR",  12.345, 98.765);
		
		store.addReview("bus-id1", 2, "Bad review", "2011-11-11", "user-id");		
		store.addReview("bus-id2", 5, "Good review", "2011-11-10", "user-id");

		String expected = "Bus Name - Austin, TX (12.345, 98.765) ()\n" + 
					"2 - Bob1: Bad review\n\n" +
					"Bus Name - Portland, OR (12.345, 98.765) ()\n" +
					"5 - Bob1: Good review";
		
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), expected, store.toString().trim());
	
	}
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testInvalidRating() {
		
		String testName = "testInvalidRating";
		YelpStore store = new YelpStore();
		
		boolean value = store.addReview("bus-id", 8, "Bad review", "2011-11-11", "user-id");
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), false, value);
		
	}
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testInvalidDate() {
		
		String testName = "testInvalidDate";
		YelpStore store = new YelpStore();
		
		boolean value = store.addReview("bus-id", 2, "Bad review", "2011-11", "user-id");
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n", testName), false, value);
		
	}
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testShortReviewsOnly() {
		
		String testName = "testShortReviewsOnline";
		YelpStore store = new YelpStore();

		Scanner infile = null;
		try {
			infile = new Scanner(new FileReader(TestUtils.INPUT_DIR + File.separator + "/yelp_academic_dataset.json"));
		} catch (FileNotFoundException e) {
			Assert.fail(String.format("%n" + "Test Case: %s%n" +
					" Error opening infile: %s%n", testName, e.getMessage()));
		}

		JSONParser parser = new JSONParser();
		while(infile.hasNextLine()) {
			String line = infile.nextLine();
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse(line);
			} catch (ParseException e) {
				Assert.fail(String.format("%n" + "Test Case: %s%n" +
						" Yelp dataset not in valid JSON format: %s%n", testName, e.getMessage()));
			}		
			if(json.get("type").equals("review")) {
				String text =  ((String)json.get("text"));
				if(text.length() > 140 || text.contains("\n")) {
					continue;
				}

				store.addReview((String)json.get("business_id"), ((Long)json.get("stars")).intValue(), (String)json.get("text"), (String)json.get("date"), (String)json.get("user_id"));
			} else if(json.get("type").equals("business")) {
				
				Double lat = (Double)json.get("latitude");
				Double lon = (Double)json.get("longitude");
				JSONArray neighborhoods = (JSONArray)json.get("neighborhoods");
				
				store.addBusiness((String)json.get("business_id"), (String)json.get("name"), (String)json.get("city"), (String)json.get("state"), lat, lon, neighborhoods);
			} else if(json.get("type").equals("user")) {
				store.addUser((String)json.get("user_id"), (String)json.get("name"));
			}			
		}

		Path expected = FileSystems.getDefault().getPath(TestUtils.OUTPUT_DIR + File.separator + "shortreviews.txt");
		Path actual = FileSystems.getDefault().getPath(TestUtils.RESULT_DIR + File.separator + "shortreviews.txt");
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
