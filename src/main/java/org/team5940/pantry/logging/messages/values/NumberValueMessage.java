package org.team5940.pantry.logging.messages.values;

import com.google.gson.JsonArray;

/**
 * A NumberValueMessage is a ValueMessage that can only take in numbers. 
 * The number is also optionally accompanied by a unit in the metadata.
 * <BR><BR>
 * The units if being told in relation to another should be x*y^-p 
 * with x being the unit of measure, y being the unit being related to, and p being the degree of y.
 * If it is "first unit per second unit to x power" then the degree has to be negative x.
 * <BR><BR>
 * E.g: m*s^-2
 * <BR>
 * This notation would be meters per second squared.
 * @author Raian
 *
 */
public class NumberValueMessage extends ValueMessage {//Units can involve more than just two others, I'd make this explanation more generic. Just talk aout being able to multiply exponents and raise individual ones to an integer power. Might also want to mention in passing the constants and method.

	/**
	 * Constant unit for meters per second squared a.k.a. acceleration.
	 */
	public static final String METERS_PER_SECOND_SQUARED_UNIT = "m*s^-2";
	
	/**
	 * Constant unit for meters per second a.k.a velocity.
	 */
	public static final String METERS_PER_SECOND_UNIT = "m*s^-1";
	
	/**
	 * Constant unit for meters.
	 */
	public static final String METERS_UNIT = "m";
	
	/**
	 * Constant unit for seconds.
	 */
	public static final String SECONDS_UNIT = "s";
	
	/**
	 * Constant unit for radians.
	 */
	public static final String RADIANS_UNIT = "rad";
	
	/**
	 * Constant unit for radians in terms of tau.
	 */
	public static final String TAU_RADIAN_UNIT = "tau*rad";
	
	/**
	 * Constant unit for angular degrees.
	 */
	public static final String DEGREES_UNIT = "deg";
	
	/**
	 * Constructor for a number value message without a unit. Adds the type "number" to the "type" JsonArray.
	 * @param source The source of the message.
	 * @param value The value to be logged.
	 */
	public NumberValueMessage(Object source, double value) {
		super(source, value);
		((JsonArray)this.getMetadata().get("type")).add("number");
	}
	
	/**
	 * Constructor for a number value message with a unit. Adds the unit to the metadata under the key "unit".
	 * @param source The source of the message.
	 * @param value The value to be logged.
	 * @param unit The unit of the value in the format of "unit"*"unit"^"power".
	 */
	public NumberValueMessage(Object source, double value, String unit) {
		this(source, value);
		this.getMetadata().addProperty("unit", unit);
	}
	
	/**
	 * Creates a unit with two given units with one in relation to the other. If the first unit is divided by the second one, the power is negative.
	 * <BR>
	 * If the power given is 0 then the method returns the unit1.
	 * <BR>
	 * If the power give is 1 then the method returns the unit1*unit2.
	 * <BR>
	 * If the power given is more than 1 then the method returns unit1*unit2^power
	 * @param unit1 The first unit in the unit relation.
	 * @param unit2 The second unit in the unit relation that is multiplied with the first.
	 * @param power The power of the second unit.
	 * @return The unit made from the inputs.
	 */
	public static String createUnit(String unit1, String unit2, int power) {
		if(power == 0) {
			return unit1;
		} else if(power == 1) {
			return unit1 + '*' + unit2;
		} else {
			return unit1 + '*' + unit2 + '^' + power;
		}
	}
}
