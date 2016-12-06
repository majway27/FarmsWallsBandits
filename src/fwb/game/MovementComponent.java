package fwb.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
 
public class MovementComponent extends Component {
 
	float direction;
	float speed;
 
	public MovementComponent( String id ) {
		this.id = id;
	}
	public float getSpeed() {
		return speed;
	}
	public float getDirection() {
		return direction;
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta) {
 
		float rotation = owner.getRotation();
		float scale = owner.getScale();
		Vector2f position = owner.getPosition();
 
		Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_A)) {
        	rotation += -0.2f * delta;
        }
        if(input.isKeyDown(Input.KEY_LEFT)) {
        	rotation += -0.2f * delta;
        }
        if(input.isKeyDown(Input.KEY_D)) {
        	rotation += 0.2f * delta;
        }
        if(input.isKeyDown(Input.KEY_RIGHT)) {
        	rotation += 0.2f * delta;
        }
        if ((input.isKeyDown(Input.KEY_W)) || (input.isKeyDown(Input.KEY_UP))){
            if ((position.y >= 56) && (position.x >= 0)) { 
            	if ((position.y <= 598) && (position.x <= 798)) {
            		float hip = -0.09f * delta;
            		position.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
            		position.y -= hip * java.lang.Math.cos(java.lang.Math.toRadians(rotation));
            		if ((position.y <= 56) || (position.x <= 0) || (position.y >= 598) || (position.x >= 798) ) {
            			if (position.y <= 56) { 
            				position.y = position.y + 70;
            			}
            			if (position.x <= 0) {
            				position.x = position.x + 70;
            			}
            			if (position.y >= 598) {
            				position.y = position.y - 70;
            			}
            			if (position.x >= 798) {
            				position.x = position.x - 70;
            			}  //sorry this is fugly
            		}
            	}
            }
        }
        if ((input.isKeyDown(Input.KEY_S)) || (input.isKeyDown(Input.KEY_DOWN))) {
        	if ((position.y >= 56) && (position.x >= 0)) { 
            	if ((position.y <= 598) && (position.x <= 798)) {
            		float hip = 0.04f * delta;
            		position.x += hip * java.lang.Math.sin(java.lang.Math.toRadians(rotation));
            		position.y -= hip * java.lang.Math.cos(java.lang.Math.toRadians(rotation));
            		if ((position.y <= 56) || (position.x <= 0) || (position.y >= 598) || (position.x >= 798) ) {
            			if (position.y <= 56) { 
            				position.y = position.y + 70;
            			}
            			if (position.x <= 0) {
            				position.x = position.x + 70;
            			}
            			if (position.y >= 598) {
            				position.y = position.y - 70;
            			}
            			if (position.x >= 798) {
            				position.x = position.x - 70;
            			}  //sorry this is fugly
            		}
            	}	
        	}
        }
		
		owner.setPosition(position);
		owner.setRotation(rotation);
		owner.setScale(scale);
	}
}