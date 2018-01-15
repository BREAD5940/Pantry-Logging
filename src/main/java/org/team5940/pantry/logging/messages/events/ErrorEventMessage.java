package org.team5940.pantry.logging.messages.events;

import org.team5940.pantry.logging.LoggingUtils;

import com.google.gson.JsonArray;

/**
 * An ErrorEventMessage is a type of EventMessage that deals with errors. 
 * Errors are critical errors and differ from warnings which are non-critical, but still should be looked at.
 * The type "error" is added and if given a throwable it will also log the "message" and the "stack_trace" of it. 
 * @author Raian
 *
 */
public class ErrorEventMessage extends EventMessage {

	/**
	 * Constructor for error event message without the throwable passed. Adds the type error to the "type" key.
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 */
	public ErrorEventMessage(Object source, String name) {
		super(source, name);
		((JsonArray)this.getMetadata().get("type")).add("error");
	}

	/**
	 * Constructor for the error event message with the throwable passed. Adds the object info of the throwable as a JsonObject which includes the "message" and "stack_trace"
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 * @param throwable The throwable that is to be logged.
	 */
	public ErrorEventMessage(Object source, String name, Throwable throwable) {
		this(source, name);
		this.getMetadata().add("throwable", LoggingUtils.getObjectInfoAsJson(throwable));
	}
}
