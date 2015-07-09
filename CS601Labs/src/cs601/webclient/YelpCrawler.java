package cs601.webclient;

import cs601.yelpapp.data.YelpStore;

/**
 * Provides methods to retrieve information about businesses from Yelp using both the web service API
 * and the human-readable web interface.
 *
 */
public class YelpCrawler {

	/**
	 * Uses a raw Java Socket to download information about the businesses specified by the IDs in the
	 * file denoted by busIdsPath and adds the business' information to the store. 
	 * Returns the store passed as input with the addition of the new data. 
	 * The following information should be added for each business:
	 * id, name, city, state, latitude, longitude, and neighborhoods
	 * Skip a business if the name, city, or state is null.
	 * The list of neighborhoods may be null. 
	 * @param busIdsPath - relative file name of a file containing business ids, one per line.
	 * @param store - a possibly non-empty YelpStore.
	 * @return
	 */
	public static YelpStore scrapeBusinesses(String busIdsPath, YelpStore store) {
		return store;
	}

	/**
	 * Uses the Yelp API to download information about the businesses specified by the IDs in the 
	 * file denoted by busIdsPath and adds the business' information to the store. 
	 * Returns the store passed as input with the addition of the new data. 
	 * The following information should be added for each business:
	 * id, name, city, state, latitude, longitude, and neighborhoods
	 * The method returns the original store if the API keys cannot be read.
	 * Skip a business if the name, city, or state is null.
	 * Skip a business if the value returned by the API is not parsable JSON.
	 * The list of neighborhoods may be null. 
	 * @param busIdsPath - relative file name of a file containing business ids, one per line.
	 * @param keyFile - relative file name of a file containing the appropriate Yelp API keys.
	 * @param store - a possibly non-empty YelpStore.
	 * @return
	 */

	public static YelpStore fetchBusinesses(String busIdsPath, String keyFile, YelpStore store) {
		return store;
	}


}
