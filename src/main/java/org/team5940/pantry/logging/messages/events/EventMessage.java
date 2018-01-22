package org.team5940.pantry.logging.messages.events;

import org.team5940.pantry.logging.LabeledObject;
import org.team5940.pantry.logging.messages.BaseMessage;

import com.google.gson.JsonArray;

/**
 * An EventMessage is a type of Message that holds events. An EventMessage must have a human readable name that is a string.
 * <BR>
 * A new JsonArray is added to the metadata under the key "type".
 * The message type is a JsonArray of hierarchical labeling akin to a {@link LabeledObject}.
 * @author Raian
 *
 */
public class EventMessage extends BaseMessage {

	/**
	 * Adds a new JsonArray to the message under the key "type" and a string for the human readable name under the key "name". 
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 */
	public EventMessage(Object source, String name) {
		super(source);
		JsonArray type = new JsonArray();
		type.add("event");
		this.getMetadata().add("type", type);
		this.getData().addProperty("name", name);
	}

}
