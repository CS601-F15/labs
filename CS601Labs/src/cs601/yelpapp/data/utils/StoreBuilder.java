package cs601.yelpapp.data.utils;


import cs601.yelpapp.data.YelpStore;

/**
 * Provides a method to concurrently build a YelpStore from local JSON files.
 *
 */
public class StoreBuilder {

	/**
	 * Recursively traverses the path specified and concurrently processes all JSON files found.
	 * Each JSON file is formatted as described here: https://www.yelp.com/academic_dataset 
	 * @param path - String representing the top-level path.
	 * @return a new YelpStore containing all data.
	 */
	public static YelpStore concurrentBuild(String path) {
		return null;
	}

}
