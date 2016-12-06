package fwb.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class MovementAiComponent extends Component {
	
	//instance movement attibutes 
	int delta;
	float direction;
	float speed;
	
	public MovementAiComponent(String id) {
		this.id = id;
	    }
	    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){ //probably nothing to render here
	    }
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta) {			
			
			float rotation = owner.getRotation();
			float scale = owner.getScale();
			Vector2f position = owner.getPosition();
	 		
			owner.setPosition(position);
			owner.setRotation(rotation);
			owner.setScale(scale);
			
		}
	/** NPC entity takes coordinates, rotation, game delta.  Makes NPC entity go */
    public static void goForward(Vector2f newposition, float rotation, int delta) {
        //Vector2f position = newPosition;  //Get position to do basic map boundry checking
    	System.out.println("You gave me target X:"+newposition.x+"Y: "+newposition.y);
    	if ((newposition.y >= 56) && (newposition.x >= 0)) { 
        	if ((newposition.y <= 598) && (newposition.x <= 798)) {
        		float hip = -0.2f * delta; //Boundry check ok, move npc entity
        		newposition.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
        		newposition.y -= hip * java.lang.Math.cos(java.lang.Math.toRadians(rotation));
        	} else System.out.println("Couldn't go there high boundry");
        } else System.out.println("Couldn't go there low boundry, correcting to: "+(newposition.y =+ 57)+","+(newposition.x =+10));  // saftey catch garbage input, correct
    }
	public float getSpeed() {
		return speed;
	}
	public float getDirection() { //TODO is this nessesary
		return direction;
	}
    public void goLeft(int delta) {
    	owner.rotation += -0.2f * delta;
    }
    public void goRight(int delta) {
    	owner.rotation += 0.2f * delta;
    }
}
