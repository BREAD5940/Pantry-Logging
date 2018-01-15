package org.team5940.pantry.logging.loggers;

import java.io.PrintStream;

import org.team5940.pantry.logging.messages.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This implementation of {@link Logger} converts each message to a single line of JSON as a string and println()s to a PrintStream.
 * For example, to output logs to the console, you could use "new PrintStreamLogger(System.out)".
 * <br>
 * The Gson object used to convert {@link Message}s to strings is built with:
 * <ul>
 * 	<li>serializeNulls()</li>
 * </ul>
 * @author David Boles
 *
 */
public class PrintStreamLogger implements Logger {

	/**
	 * Stores the Gson object used for message conversion that is created on this' initialization.
	 */
	Gson gson;
	
	/**
	 * Stores the PrintStream this outputs to.
	 */
	PrintStream output;
	
	/**
	 * Initialize a new {@link PrintStreamLogger}.
	 * @param output The PrintStream to output converted logs to. If null, this does not output.
	 */
	public PrintStreamLogger(PrintStream output) {
		if(output == null) {
			throw new IllegalArgumentException("Null Output Stream");
		}
		this.output = output;
		this.gson = (new GsonBuilder()).serializeNulls().create();
	}

	@Override
	public void log(Message message) {
		if(this.output != null && message != null) this.output.println(this.gson.toJson(message));
	}

}
