package fwb.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;

public class CollisionComponent extends Component {
	
	float distance;
	
	public CollisionComponent(String id) {
		this.id = id;
	}
	public void render(GameContainer gc, StateBasedGame sb, Graphics gr){
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
	}
	/** Build out collision detection */
	public float distanceCheck(Vector2f postion) {
		return distance;
	}
	public static boolean checkDangerCloseBandits(Vector2f mylocation, float threatdistance) {  //Proxmity check exposed, pass in victim vector, distance
		boolean risk = false; //init risk, assume false
		for (int count=0;count<2;count++){
			Vector2f threatlocation; float myX; float myY; float threatX; float threatY; 
			System.out.println("Checking array["+count+"] for distance "+threatdistance+" from " +mylocation+" to "+LocationAiComponent.Vector2fBanditLocArr[count]);
			myX = mylocation.getX(); myY = mylocation.getY();
			threatlocation = LocationAiComponent.Vector2fBanditLocArr[count];
			threatX = threatlocation.getX(); threatY = threatlocation.getY(); 
			System.out.println("Distance Check, X: "+threatX+" to "+myX+",Y: "+threatY+" to "+myY);
			if (threatdistance >= Math.abs(threatX-myX)||threatdistance >= Math.abs(threatY-myY))  {
				System.out.println("threatdistance: "+threatdistance+" is close");
				risk = true;
				break;
			} 
			else {
				System.out.println("threatdistance: "+threatdistance+" is far, check again");
				risk=false;
			} 
		} 
		if (risk) {
			System.out.println("Moving due to threatdistance");
			return true;
		} 
		else
			System.out.println("No Threat");
			return false;
	}
	public static Vector2f getNearestBandit(Vector2f mylocation, float threatdistance) { 
		Vector2f banditlocation = mylocation; 
		for (int count=0;count<2;count++){
			Vector2f threatlocation; float myX; float myY; float threatX; float threatY;
			System.out.println("Checking array["+count+"] for distance "+threatdistance+" from " +mylocation+" to "+LocationAiComponent.Vector2fBanditLocArr[count]);
			myX = mylocation.getX(); myY = mylocation.getY();
			threatlocation = LocationAiComponent.Vector2fBanditLocArr[count];
			threatX = threatlocation.getX(); threatY = threatlocation.getY(); 
			System.out.println("Distance Check, X: "+threatX+" to "+myX+",Y: "+threatY+" to "+myY);
			if (threatdistance >= Math.abs(threatX-myX)||threatdistance >= Math.abs(threatY-myY))  {
				System.out.println("threatdistance: "+threatdistance+" is close");
				banditlocation = threatlocation;
				break;
			} 
		} return banditlocation;
	}
}