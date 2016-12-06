package fwb.game;

import org.newdawn.slick.geom.Vector2f;

public class LocationAiComponent {

	static Vector2f Vector2fFarmerLocArr[] = new Vector2f[2]; //See farmer float location below
	static Vector2f Vector2fBanditLocArr[] = new Vector2f[2]; //See farmer float location below
	static Vector2f Vector2fFarmLocArr[] = new Vector2f[2]; //See farm float location below
	static Vector2f Vector2fBanditCampLocArr[] = new Vector2f[1]; //See farm float location below
	
	/** Entity Location Tracking */
	public static Vector2f getFarmerLocation(int partycount) {  //farm location getter
		Vector2f location;
		location = Vector2fFarmerLocArr[partycount];
		System.out.println("E.L.T. - Location of Entity ID "+partycount+"is "+location);
		return location;
	}
	public static void setFarmerLocation(int partycount, Vector2f location) { //farm location setter
		Vector2fFarmerLocArr[partycount] = location;
		System.out.println("ELT - Location of Entity ID "+partycount+"("+location+") is set");
	}
	//Bandits
	public static Vector2f getBanditLocation(int partycount) {  //farm location getter
		Vector2f location;
		location = Vector2fBanditLocArr[partycount];
		System.out.println("E.L.T. - Location of Entity ID "+partycount+"is "+location);
		return location;
	}
	public static void setBanditLocation(int partycount, Vector2f location) { //farm location setter
		Vector2fBanditLocArr[partycount] = location;
		System.out.println("ELT - Location of Entity ID "+partycount+"("+location+") is set");
	}
	//Farms
	public static Vector2f getFarmLocation(int partycount) {  //farm location getter
		Vector2f location;
		location = Vector2fFarmLocArr[partycount];
		System.out.println("E.L.T. - Location of Entity ID "+partycount+"is "+location);
		return location;
	}
	public static void setFarmLocation(int partycount, Vector2f location) { //farm location setter
		Vector2fFarmLocArr[partycount] = location;
		System.out.println("ELT - Location of Entity ID "+partycount+"("+location+") is set");
	}
	//Bandit camps
	public static Vector2f getBanditCampLocation(int partycount) {  //farm location getter
		Vector2f location;
		location = Vector2fBanditCampLocArr[partycount];
		System.out.println("E.L.T. - Location of Entity ID "+partycount+"is "+location);
		return location;
	}
	public static void setBanditCampLocation(int partycount, Vector2f location) { //farm location setter
		Vector2fBanditCampLocArr[partycount] = location;
		System.out.println("ELT - Location of Entity ID "+partycount+"("+location+") is set");
	}
}
