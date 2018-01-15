package org.team5940.pantry.logging.messages;

import org.team5940.pantry.logging.LoggingUtils;

import com.google.gson.JsonObject;

/**
 * A BaseMessage is a Message with only very basic metadata that all logs use. 
 * The metadata that is stored includes time, runtime, thread, and source.
 * @author Raian
 *
 */
public class BaseMessage extends Message {//TODO just like LoggingUtils' obInfo, add the JSON type and how the information is obtained, might want to format it as a list too.

	/**
	 * Constructs a new message with the source passed through it and adds basic information from LoggingUtils.
	 * @param source The source of the message.
	 */
	public BaseMessage(Object source) {
		
		super(new JsonObject(), new JsonObject());
		
		this.getMetadata().addProperty("time", LoggingUtils.getTime());
		this.getMetadata().addProperty("runtime", LoggingUtils.getRuntime());
		this.getMetadata().add("thread", LoggingUtils.getCurrentThreadAsJson());
		this.getMetadata().add("source", LoggingUtils.getObjectInfoAsJson(source));
		
	}

}
