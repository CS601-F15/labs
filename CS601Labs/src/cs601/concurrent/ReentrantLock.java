package cs601.concurrent;


/**
 * A read/write lock that allows multiple readers, disallows multiple writers, and allows a writer to 
 * acquire a read lock while holding the write lock. 
 * 
 */
public class ReentrantLock {

	/**
	 * Construct a new ReentrantLock.
	 */
	public ReentrantLock() {
	}

	/**
	 * Returns true if the invoking thread holds a read lock.
	 * @return
	 */
	public synchronized boolean hasRead() {
		return false;
	}

	/**
	 * Returns true if the invoking thread holds a write lock.
	 * @return
	 */
	public synchronized boolean hasWrite() {
		return false;
	}

	/**
	 * Non-blocking method that attempts to acquire the read lock.
	 * Returns true if successful.
	 * @return
	 */
	public synchronized boolean tryLockRead() {
		return false;
	}

	
	/**
	 * Non-blocking method that attempts to acquire the write lock.
	 * Returns true if successful.
	 * @return
	 */
	public synchronized boolean tryLockWrite() {
		return false;
	}

	/**
	 * Blocking method that will return only when the read lock has been 
	 * acquired.
	 */
	public synchronized void lockRead() {
	}

	/**
	 * Releases the read lock held by the calling thread. Other threads may continue
	 * to hold a read lock.
	 */
	public synchronized void unlockRead() {
	}

	/**
	 * Blocking method that will return only when the write lock has been 
	 * acquired.
	 */
	public synchronized void lockWrite() {
	}

	/**
	 * Releases the write lock held by the calling thread. The calling thread may continue to hold
	 * a read lock.
	 */

	public synchronized void unlockWrite() {
	}
}