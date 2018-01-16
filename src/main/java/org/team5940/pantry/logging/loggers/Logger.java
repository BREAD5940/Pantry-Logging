package org.team5940.pantry.logging.loggers;

import org.team5940.pantry.logging.messages.Message;
import org.team5940.pantry.logging.messages.events.ErrorEventMessage;

/**
 * Interface to be implemented for objects that process/ send/ store messages.
 * 
 * @author Raian
 *
 */
public interface Logger {

	/**
	 * Logs the provided message. If the message is null, it logs nothing.
	 * 
	 * @param message
	 *            The message to be logged.
	 */
	public void log(Message message);

	public default void throwError(Object source, RuntimeException throwable) {
		ErrorEventMessage errorMessage = new ErrorEventMessage(source, throwable);
		this.log(errorMessage);
		throw throwable;
	}
}
