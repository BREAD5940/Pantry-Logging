package org.team5940.pantry.logging;

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
	 * Gets the current thread that is running as represented by a JsonObject. It is converted to Json by {@link #getObjectInfoAsJson(Object) getObjectInfoAsJson}.
	 * @return The current thread.
	 */
	public static JsonElement getCurrentThreadAsJson() {
		
		return getObjectInfoAsJson(Thread.currentThread());

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
	 * <LI>Thread</LI><UL><LI>"thread" - Thread Name</LI></UL>
	 * <LI>LabeledObject</LI><UL><LI>"label" - Label for the class</LI></UL>
	 * <LI>Array</LI><UL><LI>"type" - Name of the component type</LI></UL>
	 * <LI>Throwable</LI><UL><LI>"message" - The message of the throwable object</LI><LI>"stack_trace" - The stack trace of the throwable</LI>
	 * </UL>
	 * @param object The object to return information about.
	 * @return The information about the object. If the argument is null, it returns an instance of JsonNull.
	 */
	public static JsonElement getObjectInfoAsJson(Object object) {//TODO Add JSON type information and information source information to doc section on classes
		
		if(object == null) { 
			return JsonNull.INSTANCE;
		}
		
		Class<? extends Object> c = object.getClass();
		
		if(Number.class.isAssignableFrom(c)) {
			return new JsonPrimitive((Number)object);
		}
		
		if(String.class.isAssignableFrom(c)) {
			return new JsonPrimitive((String)object);
		}
		
		if(Character.class.isAssignableFrom(c)) {
			return new JsonPrimitive((Character)object);
		}
		
		if(Boolean.class.isAssignableFrom(c)) {
			return new JsonPrimitive((Boolean)object);
		}
		
		
		JsonObject json = new JsonObject();
		json.addProperty("class", object.getClass().getName());
		
		if(Thread.class.isAssignableFrom(c)) {
			json.addProperty("thread", ((Thread)object).getName());
		}
		
		if(LabeledObject.class.isAssignableFrom(c)) {
			json.add("label", ((LabeledObject)object).getLabel());
		}
		
		if(c.isArray()) {
			json.addProperty("type", c.getComponentType().getName());
		}
		
		if(Throwable.class.isAssignableFrom(c)) {
			json.addProperty("message",((Throwable)object).getMessage());
			JsonArray stackJson = new JsonArray();
			for(StackTraceElement element:((Throwable)object).getStackTrace()) {
				stackJson.add(element.toString());
			}
			json.add("stack_trace", stackJson);
		}
		
		return json;
		
	}
	
}
