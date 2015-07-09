import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cs601.yelpapp.data.YelpStore;


public class ComplexYelpStoreTest {

	private static YelpStore store;
	private static List<String> businesses;
	private static HashMap<String, Double> ratings;
	private static List<String> users;


	@Test(timeout = TestUtils.TIMEOUT)
	public void testCompleteStore() {
		String testName = "testCompleteStore";

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

	@Test(timeout = TestUtils.TIMEOUT)
	public void testGetBusinesses() {
		String testName = "testGetBusinesses";

		List<String> actual = store.getBusinesses();
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
				" Mismatched number of businesses %n", testName), businesses.size(), actual.size());
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
				" Mismatched business name.%n", testName), businesses, actual);

	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testGetRating() {
		String testName = "testGetRating";
		for(String name: ratings.keySet()) {
			Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
					" Incorrect rating .%s%n", testName, name), ((Double)ratings.get(name)), ((Double)store.getRating(name)));
		}
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testGetRatingZero() {
		String testName = "testGetRatingZero";
		for(String name: ratings.keySet()) {
			Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
					" Incorrect rating .%s%n", testName, name), ((Double)0.0), ((Double)store.getRating("NO BUSINESS EXISTS")));
		}
	}

	@Test(timeout = TestUtils.TIMEOUT)
	public void testGetUsers() {
		String testName = "testGetUsers";
		List<String> actual = store.getUsers("w73NcxMQmDsqVKu3zVSJtg");
		
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
				" Incorrect list of users. %n", testName), users, actual);
	}
	
	@Test(timeout = TestUtils.TIMEOUT)
	public void testGetUsersEmpty() {
		String testName = "testGetUsersEmpty";
		List<String> actual = store.getUsers("NO BUSINESS EXISTS");
		List<String> expected = new ArrayList<>();
		
		Assert.assertEquals(String.format("%n" + "Test Case: %s%n" +
				" Incorrect list of users. %n", testName), expected, actual);
	}
 
	@BeforeClass 
	public static void setUp() {
		String testName = "TestComplex-setUp";
		store = new YelpStore();
		businesses = new ArrayList<String>();

		ratings = new HashMap<>();
		ratings.put("yqUjOo6x2CO6hBh_o58gSw", 5.0);
		ratings.put("zZNTfwz8tR86k-cTqPxvDw", 3.25);
		ratings.put("4Lcrd7xr5aLBetLmi-INeA", 1.5);
		ratings.put("4lkx2UdISL1-2hfcTYI0lg", 2.75);
		ratings.put("Qxle1jEskVde-Sn5Ntezmw", 4.5);

		users = new ArrayList<String>();

		users.add("Ed T.");
		users.add("Andrew T.");
		users.add("Conor O.");
		users.add("Steve M.");
		users.add("Michael H.");
		users.add("Max E.");
		users.add("Steve h.");
		
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
				store.addReview((String)json.get("business_id"), ((Long)json.get("stars")).intValue(), (String)json.get("text"), (String)json.get("date"), (String)json.get("user_id"));
			} else if(json.get("type").equals("business")) {
				String name = (String)json.get("name");
				String busId = (String)json.get("business_id");
				Double lat = (Double)json.get("latitude");
				Double lon = (Double)json.get("longitude");
				JSONArray neighborhoods = (JSONArray)json.get("neighborhoods");
				store.addBusiness(busId, name, (String)json.get("city"), (String)json.get("state"), lat, lon, neighborhoods);
				if(!businesses.contains(busId)) {
					businesses.add(busId);
				}				
			} else if(json.get("type").equals("user")) {
				store.addUser((String)json.get("user_id"), (String)json.get("name"));
			}			
		}
		
		Collections.sort(businesses);

	}


}
