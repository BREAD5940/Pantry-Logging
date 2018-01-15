package org.team5940.pantry.logging.messages.events;

import org.team5940.pantry.logging.LoggingUtils;

import com.google.gson.JsonArray;

/**
 * An InitEventMessage is type of EventMessage that stores information from a part of code being initialized.
 * <BR>
 * The arguments passed through the constructor are given as an array of objects and 
 * logged as data to the "args" key as a JsonArray.
 * @author Raian
 *
 */
public class InitEventMessage extends EventMessage{//add getObInfo...

	/**
	 * Creates a new initialization message with the arguments used during initialization passed through the constructor as the key "args" and appends the init tag to the "type" key.
	 * @param source The source of the message.
	 * @param name The human readable name to be appended to the message.
	 * @param arguments The arguments passed through the constructor during initialization.
	 */
	public InitEventMessage(Object source, String name, Object[] arguments) {//Technically you're not appending it to the key
		super(source, name);
		
		((JsonArray)this.getMetadata().get("type")).add("init");
		
		JsonArray args = new JsonArray();
		for(Object argument:arguments) {
			args.add(LoggingUtils.getObjectInfoAsJson(argument));
		}
		this.getData().add("args", args);
	}

}
