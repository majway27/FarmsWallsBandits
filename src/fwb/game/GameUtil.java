package fwb.game;

import org.newdawn.slick.geom.Vector2f;


public class GameUtil {
	
	static String message = "Stub";  //Default value
	static int intPopulationCntArr[] = new int[7]; //TODO See population counters below
	/**static Vector2f Vector2fFarmerLocArr[] = new Vector2f[2]; //See farmer float location below
	static Vector2f Vector2fBanditLocArr[] = new Vector2f[2]; //See farmer float location below
	static Vector2f Vector2fFarmLocArr[] = new Vector2f[2]; //See farm float location below
	static Vector2f Vector2fBanditCampLocArr[] = new Vector2f[1]; //See farm float location below
	*/
	static Vector2f holder = new Vector2f(); //TODO remove?
	
/** Messaging
 	* TODO - Add message bus for status out to screen.  Other classes should buffer up status messages here 
 	*/
	public static String getMessage() { //get message
		return message;
	}
	public static void setMessage(String newMessage) { //set message
		message = newMessage;
	}
/** Entity Population Tracking
 	* ODO Population counters 
	 * Track the following items:
	 * [0] farmers
	 * [1] farms
	 * [2] Towns
	 * [3] Keeps
	 * [4] Bandits
	 * [5] Banditcamps
	 * [6] Farmers Killed
	 * [7] Farms Burned
	 */
	//setter - pass a corresponding element and increment/dec it
	//getter - pass an element, loop through,pull,return
}
