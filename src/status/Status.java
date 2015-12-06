package status;
/**
 * Interface for the status 
 * <p>
 * [status]
 * <p>
 */
public interface Status {
	/**
	 */
	
	/** The main loop for the application
	 * @param delta	Time passed from the last loop
	 */
	public void doLoop(long delta);
}
