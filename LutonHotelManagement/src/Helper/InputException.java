/**
 * This class throws the exception when there is input error.
 * It has a custom message field which can be set and same message will be passed.
 */
package Helper;

/**
 * @author AjayaSharma
 *
 */
public class InputException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InputException() {
		super("The data you entered is not correct. Please check the input");
	}

	/**
	 * @param message
	 */
	public InputException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InputException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InputException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
