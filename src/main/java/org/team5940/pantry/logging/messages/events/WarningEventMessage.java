package org.team5940.pantry.logging.messages.events;

import org.team5940.pantry.logging.LoggingUtils;

import com.google.gson.JsonArray;

/**
 * A WarningEventMessage is type of {@link EventMessage} that deals with warnings.
 * Warnings are non-critical errors, but are still things that should be looked at.
 * The type "warning" is added and if given a throwable it will also add the "message" and the "stack_trace" of it. 
 * @author Raian
 *
 */
public class WarningEventMessage extends EventMessage {
	
	/**
	 * Constructor for warning event message without the throwable passed. Adds the type warning to the "type" key.
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 */
	public WarningEventMessage(Object source, String name) {
		super(source, name);
		((JsonArray)this.getMetadata().get("type")).add("warning");
	}
	
	/**
	 * Constructor for the warning event message with the throwable passed. Adds the object info of the throwable as a JsonObject which includes the "message" and "stack_trace"
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 * @param throwable The throwable that is to be logged.
	 */
	public WarningEventMessage(Object source, String name, Throwable throwable) {
		this(source, name);
		this.getData().add("throwable", LoggingUtils.getObjectInfoAsJson(throwable));
	}
	
	/**
	 * Constructor for the warning event message with the throwable passed. Adds the object info of the throwable as a JsonObject which includes the "message" and "stack_trace"
	 * The name for this is the message in the throwable. 
	 * @param source The source of the message.
	 * @param throwable The throwable that is to be logged.
	 */
	public WarningEventMessage(Object source, Throwable throwable) {
		this(source, throwable.getMessage(), throwable);
	}

}
