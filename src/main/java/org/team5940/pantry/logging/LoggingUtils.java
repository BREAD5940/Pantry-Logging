package org.team5940.pantry.logging;

import org.team5940.pantry.logging.messages.Message;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Static utilities for basic logging needs.
 * @author Raian
 *
 */
public class LoggingUtils {
	
	//TODO s:
	//Link to constructor calls in docs
	//Make things links in docs
	//Anything that's not primitive as an argument to a method or contructor needs docs about what it does if it's null (either how the program recovers or that an exception is thrown)

	public static final JsonArray EVENT_TYPE = new JsonArray();
	
	public static final Gson gson = new Gson();
	
	/**
	 * Unix time stamp in milliseconds as a long.
	 * @return The current time.
	 */
	public static long getTime() {
		
		return System.currentTimeMillis();
		
	}
	
	/**
	 * The System.nanoTime of the start of the robot.
	 */
	static long startTime = System.nanoTime();
	
	/**
	 * The running time of the robot in milliseconds starting from zero.
	 * @return The runtime.
	 */
	public static long getRuntime() {
		
		return (System.nanoTime() - startTime)/1000;
		
	}
	
	/**
	 * Gets the current thread that is running as represented by a JsonObject. It is converted to Json by getting the name of the thread and returning it as a JsonObject.
	 * @return The current thread.
	 */
	public static String getCurrentThreadAsJson() {
		Thread thread = Thread.currentThread();
		return thread.getName();
		

	}
	
	/**
	 * Gets the information of an object as a JsonObject. If object is null it return an instance of JsonNull. The information includes the following:<br>  
	 * <UL>
	 * <LI>If object is null - returns JsonNull.</LI>
	 * <LI>If object's class is assignable to Number - returns JsonPrimitive of the number.</LI>
	 * <LI>If object's class is assignable to String - returns JsonPrimitive of the string.</LI>
	 * <LI>If object's class is assignable to Character - returns JsonPrimitive of the character.</LI>
	 * <LI>If object's class is assignable to Boolean - returns JsonPrimitive of the boolean.</LI>
	 * </UL>
	 * Otherwise, it returns a JsonObject with the following properties:<br>
	 * <UL>
	 * <LI>"class" - Class Name
	 * <LI>LabeledObject</LI> <LI>"label" - Label for the class</LI>
	 * <LI>Array</LI> <LI>"type" - Name of the component type</LI>
	 * </UL>
	 * @param object The object to return information about.
	 * @return The information about the object. If the argument is null, it returns an instance of JsonNull.
	 */
	public static JsonElement getObjectInfoAsJson(Object object) {//TODO Add JSON type information and information source information to doc section on classes
		
		if(object == null) { 
			return JsonNull.INSTANCE;
		}
				
		if(object instanceof Number) {
			return new JsonPrimitive((Number)object);
		}
		
		if(object instanceof String) {
			return new JsonPrimitive((String)object);
		}
		
		if(object instanceof Character) {
			return new JsonPrimitive((Character)object);
		}
		
		if(object instanceof Boolean) {
			return new JsonPrimitive((Boolean)object);
		}
		
		
		JsonObject json = new JsonObject();
		json.addProperty("class", object.getClass().getName());
		
		if(object instanceof LabeledObject) {
			json.add("label", ((LabeledObject)object).getLabel());
		}
		
		if(object.getClass().isArray()) {
			json.addProperty("type", object.getClass().getComponentType().getName());
		}
		
		return json;
		
	}
	
	/**
	 * Gets the message and stack trace of a Throwable and adds it to the target message.
	 * @param target The target to add the message and stack trace onto.
	 * @param throwable The throwable that the message and stack trace are being gotten from.
	 */
	
	public static void getThrowableAsJson(Message target, Throwable throwable) throws IllegalArgumentException {
		
		target.getData().addProperty("message", throwable.getMessage());
		
		JsonArray stackJson = new JsonArray();
		for(StackTraceElement element:throwable.getStackTrace()) {
			stackJson.add(element.toString());
		}
		target.getData().add("stack_trace", stackJson);
		
	}
	
	public static JsonArray chainPut(JsonArray jsonArray, Number number) {
		jsonArray.add(number);
		return jsonArray;
	}
	
	public static JsonArray chainPut(JsonArray jsonArray, String string) {
		jsonArray.add(string);
		return jsonArray;
	}
	
	public static JsonArray chainPut(JsonArray jsonArray, Boolean bool) {
		jsonArray.add(bool);
		return jsonArray;
	}
	
	public static JsonArray chainPut(JsonArray jsonArray, Character character) {
		jsonArray.add(character);
		return jsonArray;
	}
	
	public static JsonArray chainPut(JsonArray jsonArray, JsonElement jsonElement) {
		jsonArray.add(jsonElement);
		return jsonArray;
	}
	
	/**
	 * Checks an argument to see if it is null. Should only be used for checking
	 * constructor arguments for nodes.
	 * 
	 * @param argument
	 *            The argument to check if null.
	 * @throws IllegalArgumentException
	 *             If the argument is null.
	 */
	public static void checkArgument(Object argument) throws IllegalArgumentException {
		if (argument == null) {
			// TODO maybe log?
			throw new IllegalArgumentException("Null Argument");
		}
	}
	
	/**
	 * Checks array to see if it is null, and then checks each of its individual
	 * values to see if it is null. Should only be used in constructor.
	 * 
	 * @param arguments
	 *            Array to check if it or any of its values are null.
	 * @throws IllegalArgumentException
	 *             Throws if any value is null.
	 */
	public static void checkArrayArguments(Object... arguments) throws IllegalArgumentException {
		checkArgument(arguments);
		for (Object argument : arguments) {
			if (argument == null) {
				throw new IllegalArgumentException("Array Argument Null");
			}
		}
	}
}
