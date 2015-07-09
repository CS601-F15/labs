package cs601.yelpapp.data;

import java.nio.file.Path;
import java.util.List;

import org.json.simple.JSONArray;

/**
 * Data structure to store information about businesses, users, and reviews.
 *
 */
public class YelpStore {

	/**
	 * Default constructor.
	 */
	public YelpStore() {
	}

	/**
	 * Add a new review.
	 * @param businessId - ID of the business reviewed.
	 * @param rating - integer rating 1-5.
	 * @param review - text of the review.
	 * @param date - date of the review in the format yyyy-MM-dd, e.g., 2015-05-25.
	 * @param userId - ID of the user writing the review.
	 * @return true if successful, false if unsuccessful because of invalid date or rating.
	 */
	public boolean addReview(String businessId, int rating, String review, String date, String userId) {

		return false;
	}

	/**
	 * Add a new business. Assumes the business has no neighborhood information.
	 * @param businessId - ID of the business.
	 * @param name - name of the business.
	 * @param city - city where the business is located.
	 * @param state - state where the business is located.
	 * @param lat - latitude of business location.
	 * @param lon - longitude of business location.
	 * @return true if successful.
	 */
	public boolean addBusiness(String businessId, String name, String city, String state, double lat, double lon) {
		return false;
	}
	
	/**
	 * Add a new business.
	 * @param businessId - ID of the business.
	 * @param name - name of the business.
	 * @param city - city where the business is located.
	 * @param state - state where the business is located.
	 * @param lat - latitude of business location.
	 * @param lon - longitude of business location.
	 * @param neighborhoods - JSONArray containing a list of neighborhoods where the business is located.
	 * @return true if successful.
	 */
	public boolean addBusiness(String businessId, String name, String city, String state, double lat, double lon, JSONArray neighborhoods) {
		return false;
	}

	/**
	 * Add a new business.
	 * @param businessId - ID of the business.
	 * @param name - name of the business.
	 * @param city - city where the business is located.
	 * @param state - state where the business is located.
	 * @param lat - latitude of business location.
	 * @param lon - longitude of business location.
	 * @param neighborhoods - comma separated String containing a list of neighborhoods
	 * @return true if successful.
	 */
	public boolean addBusiness(String businessId, String name, String city, String state, double lat, double lon, String neighborhoods) {
		return false;
	}

	
	/**
	 * Add a new user.
	 * @param userId - ID of the user.
	 * @param name - name of the user (e.g., Sami R.)
	 * @return true if successful.
	 */
	public boolean addUser(String userId, String name) {
		return false;
	}

	/**
	 * Return a string representation of the data store. Format must be as follows:
	 	Business1 Name - City, State (lat, lon) (neighborhood1, neighborhood2)
		Rating - User: Review
		Rating - User: review
		Business2 Name - City, State (lat, lon) (neighborhood1, neighborhood2)
		Rating - User: Review
		Rating - User: review
	 * Adhere to the following rules in generating the output:
	 * 1. Business Names must be sorted alphabetically.
	 * 2. Ratings for a particular business must be sorted by date.
	 * 3. Only reviews for businesses that have been added will be displayed.
	 * 4. If a review is written by user U and no user U has been added to the store the review will appear with no name.
	 * 5. Neighborhoods are sorted alphabetically.
	 * @return string representation of the data store
	 */
	public String toString() {
	
		return null;
	}

	/**
	 * Save the string representation of the data store to the file specified by fname.
	 * This method must use the YelpStore toString method.
	 * @param fname - path specifying where to save the output.
	 */
	public void printToFile(Path fname) {

	}

	/**
	 * Return an alphabetized list of the ids of all businesses in the data store.
	 * @return
	 */
	public List<String> getBusinesses() {
		return null;
	}

	/**
	 * Return the average rating for the given business name.
	 * @param business - name of the business (not ID).
	 * @return average rating or 0 if no ratings for the business.
	 */
	public double getRating(String busId) {
		return 0.0;
	}

	/**
	 * Return the list of names of all users reviewing the business.
	 * @param busId - ID of the business.
	 * @return List of user names or empty list if no reviews.
	 */
	public List<String> getUsers(String busId) {		
		return null;
	}	
} 
