package cs601.concurrent;

public class WorkQueue {

	/**
	 * Construct a WorkQueue with 10 default workers.
	 */
	public WorkQueue() {
	}


	public WorkQueue(int nThreads)
	{
	}

	/**
	 * Execute a new Runnable job.
	 * @param r
	 */
	public void execute(Runnable r) {

	}

	/**
	 * Stop accepting new jobs.
	 * This method should not block until work is complete.
	 */
	public void shutdown() {    			
	}

	/**
	 * Block until all jobs in the queue are complete.
	 */
	public void awaitTermination() {
	}

}