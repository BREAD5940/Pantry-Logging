package org.team5940.pantry.logging;

import com.google.gson.JsonArray;

/**
 * A Label is a simple, hierarchical, string-based name for something - similar to urls, file structures, 
 * addresses, etc. There are no limitations on the strings that can be used other than that they cannot be null. 
 * The hierarchy is determined as least specific/ most general -> most specific/ least general.
 * <BR>
 * (e.g. world->country->state->county->street->address)
 * <BR><BR>
 * A LabeledObject is an object that has a label. Object that implement labeled objects have a getLabel method in 
 * order to return the label that corresponds with that object.
 * The label is stored as a JsonArray of Strings in the matter described above.
 * @author Raian
 *
 */
public interface LabeledObject {
	
	/**
	 * A method that gets the label of the object.
	 * @return The label.
	 */
	public JsonArray getLabel();
}
