package fwb.game;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class Entity {
	
	
	private String state; //TODO - thinking we can track alive/dead/sleeping/building/etc here
	private String name;		
	int partycount; //this is a pile for each entity type ie 1/2 farmers, 2/3 farms, etc.  Used in location tracking
	private int xloc;
	private int yloc;
	String id;
	Vector2f position;
    float scale;
    float rotation;
    RenderComponent renderComponent = null;
    ArrayList<Component> components = null;
    
    
    public Entity(String id, int partycount) {
        this.id = id; this.partycount=partycount; //Attach passed id and partycount to this entity's attributes
        components = new ArrayList<Component>();
        position = new Vector2f(0,0);
        scale = 1; //can scale size, this is int so not very fine grained
        rotation = 0;
        this.spawnEntity(); //When called in a entity constructor, spawn randomly on map, sE also sets position
        if (this.id.equals("farmer")) {
        	LocationAiComponent.setFarmerLocation(this.partycount, this.getPosition()); //register location with MovementAiComponent telephone book
        }
		if (this.id.equals("bandit")) {
			LocationAiComponent.setBanditLocation(this.partycount, this.getPosition()); //register location with MovementAiComponent telephone book
		}
		if (this.id.equals("farm")) {
			LocationAiComponent.setFarmLocation(this.partycount, this.getPosition()); //register location with MovementAiComponent telephone book
		}
		if (this.id.equals("banditcamp")) {
			LocationAiComponent.setBanditCampLocation(this.partycount, this.getPosition()); //register location with MovementAiComponent telephone book
		}
    }
    //public void birthEntity() {
    	//TODO build out to pop into a stack of entities, or maybe use as a way to clump friendlies when spawning
    //}
	public void spawnEntity(){  //spawn entity, assumes random placement like hero starting new game
		int randomnumber = 0;
		Random random = new Random();
		int xmax = 760; int xmin = 20; //x range
		randomnumber = random.nextInt(xmax - xmin) + xmin; //randomly pick x
		xloc = randomnumber;
		int ymax = 540;	int ymin = 70; //y range 
		randomnumber = random.nextInt(ymax - ymin) + ymin; //randomly pick y
		yloc = randomnumber;
		this.setPosition(new Vector2f(xloc, yloc)); //Put player somewhere in game container canvas
	}
	public void dropEntity(int newxloc, int newyloc){  //Takes postion as argument, assumes resuming a saved game or purposeful directed spawn
		yloc = newyloc;
		xloc = newxloc;
		//TODO is this method needed?
	}
	public void AddComponent(Component component) {  //set component
        if(RenderComponent.class.isInstance(component))
            renderComponent = (RenderComponent)component;
        component.setOwnerEntity(this);
	components.add(component);
    }
    public Component getComponent(String id) {  //get components
        for(Component comp : components) {
	    if ( comp.getId().equalsIgnoreCase(id) )
	        return comp;
        } return null;
    }
		
	//Common attributes for mobile and stationary objects
	// Metadata
	public String getId() {  //get id
    	return id;
	}
	public String getName() { //get name
		return name;
	}
	public void setName(String newname) { //set name
		name = newname;
	}
	public String getState() { //get state
		return state;
	}
	public void setState(String newstate) { //set state
		state = newstate;
	}
	
	//Movement and location attributes
	public Vector2f getPosition() {  //get position
		return position;
	}
	public void setPosition(Vector2f position) {  //set position
		this.position = position;
	}
	//get xloc
	public int getXLoc() {
		return xloc;
	}
	//get yloc
	public int getYLoc() {
		return yloc;
	}
	//set xloc
	public void moveXLoc(int newxloc){
		xloc = xloc + newxloc;	
	}
	//set yloc
	public void moveYLoc(int newyloc){
		yloc = yloc + newyloc;
	}
    //scale
	public float getScale(){  //get scale
	return scale;
    }
    public void setScale(float scale) {  //set scale
	this.scale = scale;
    }
	//rotation
    public float getRotation(){ //get rotation
	return rotation;
    }
    public void setRotation(float rotate) {  //set rotation
        rotation = rotate;
    }
    
    public void update(GameContainer gc, StateBasedGame sb, int delta) {
        for(Component component : components) {
            component.update(gc, sb, delta);
        }
    }
 
    public void render(GameContainer gc, StateBasedGame sb, Graphics gr) {
        if(renderComponent != null)
            renderComponent.render(gc, sb, gr);
    }
}
