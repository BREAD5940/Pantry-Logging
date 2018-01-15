package org.team5940.pantry.logging.messages.values;

import org.team5940.pantry.logging.LabeledObject;
import org.team5940.pantry.logging.LoggingUtils;
import org.team5940.pantry.logging.messages.BaseMessage;

import com.google.gson.JsonArray;

/**
 * A value message is a type of message that holds a value. A value can be any object and its object info is logged.
 * <BR>
 * A new JsonArray is added to the metadata under the key "type".
 * The message type is a label akin to a {@link LabeledObject}. This label starts with a value and can be appended to by sub-types.
 * @author Raian
 *
 */
public class ValueMessage extends BaseMessage {

	/**
	 * Constructor for a value message. Adds the type "value" to the "type" JsonArray and getObjectInfoAsJson of the value to the data.
	 * <BR>
	 * {@link BaseMessage#BaseMessage BaseMessage} logs the source of the message.
	 * @param source The source of the message.
	 * @param value The value to be logged.
	 */
	public ValueMessage(Object source, Object value) {
		super(source);
		JsonArray type = new JsonArray();
		type.add("value");
		this.getMetadata().add("type", type);
		this.getData().add("value", LoggingUtils.getObjectInfoAsJson(value));
	}
	

}
