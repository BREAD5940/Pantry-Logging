package org.team5940.pantry.logging.messages;

import com.google.gson.JsonObject;

/**
 * A message is a single component of a log that represents some information that is being logged. 
 * Messages are composed of metadata which stores useful information relating to the context of the message and 
 * data representing the message's information. Both metadata and data are stored as Gson JsonObjects and are 
 * mutable (getting either of them and editing it will edit the contents of the message).
 * @author Raian
 *
 */
public class Message {
	
	/**
	 * The metadata of the message.
	 */
	JsonObject metadata;
	
	/**
	 * The data of the message.
	 */
	JsonObject data;
	
	/**
	 * Constructs a new {@link Message} with the provided metadata and data or with new JsonObjects if they are null.
	 * @param metadata The metadata as a JsonObject.
	 * @param data The data as a JsonObject.
	 */
	public Message(JsonObject metadata, JsonObject data) {
		if(metadata == null) {
			this.metadata = new JsonObject();
		} else {
			this.metadata = metadata;
		}
		if(data == null) {
			this.data = new JsonObject();
		} else {
			this.data = data;
		}
	}

	/**
	 * A method that returns the metadata of the message.
	 * @return The metadata of the message as a JsonObject.
	 */
	public JsonObject getMetadata() {
		return metadata;
	}
	
	/**
	 * A method that returns the data of the message.
	 * @return The data of the message as a JsonObject.
	 */
	public JsonObject getData() {
		return data;
	}
}
