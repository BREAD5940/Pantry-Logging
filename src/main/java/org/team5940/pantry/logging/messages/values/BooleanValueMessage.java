package org.team5940.pantry.logging.messages.values;

import com.google.gson.JsonArray;

/**
 * A BooleanValueMessage is a {@link ValueMessage#ValueMessage(Object, Object) ValueMessage} that can only take boolean values. 
 * It is the same as a {@link ValueMessage#ValueMessage(Object, Object) ValueMessage}, but adds the type boolean and the constructor only accepts booleans.
 * @author Raian
 *
 */
public class BooleanValueMessage extends ValueMessage {
	
	/**
	 * Constructor for a value message. Adds the type "boolean" to the "type" JsonArray.
	 * @param source The source of the message.
	 * @param value The boolean value to be logged.
	 */
	public BooleanValueMessage(Object source, boolean value) {
		super(source, value);
		((JsonArray)this.getMetadata().get("type")).add("boolean");
	}

}
