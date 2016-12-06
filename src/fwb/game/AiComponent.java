package fwb.game;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public class AiComponent extends Component{
	
	
	//instance ai attributes
	boolean idle = true;
	boolean tired = false;
	boolean fear = false;
	int sleeptimer = 0;
	int buildtimer = 0;
	int choice = 0;

	
	public AiComponent(String id) {
		this.id = id;
	    }
	    public void render(GameContainer gc, StateBasedGame sb, Graphics gr){ //probably nothing to render here
	    }
		@Override
		public void update(GameContainer gc, StateBasedGame sb, int delta) {			
			
			//float rotation = owner.getRotation();
			//float scale = owner.getScale();
			//Vector2f position = owner.getPosition();
	 		
			actNow(delta);
			//owner.setPosition(position);
			//owner.setRotation(rotation);
			//owner.setScale(scale);
			
		}
		/** High Level Flow idea
		 * sequentially walk through loop of "turns" for each entity
		 * so - in update method for each NPC entity, we
		 * 1) call ai
		 * 2) ai evaluates snapshot of game condition as pertinent to entity (ie farms can't react to bandits so no work there)
		 * 3) invoke component methods to carry out AI directive
		 * 4) rinse, lather, repeat in a turn based approach that appears realtime to player
		 */
		public void actNow(int delta) {
			/** Crude AI 
			 * 1 - Move (implies an attack if bandit)
			 * 2 - Stay "sleep"
			 * 3 - Build farm/camp
			*/
			if (idle) {  //if entity isn't busy with task below, catch idle flag and get work, then set idle false heading into switch block
				if (this.id.equals("farmerAi")) {
					/** Farmer Rules
					 * If I have slept lasttime (bool) then
					 * 	else sleep
					 * if I am not near a bandit then
					 *  else run
					 * build a farm (farmers should always be farming or running to live another day farming)
					 */
					if (!tired) {  //If I have slept lasttime (bool) then
						if (!farmerRisk()){ //if I am not near a bandit then
							choice=3; //build a farm (farmers should always be farming or running to live another day farming)
						} else {choice=1; fear=true;} //else run, flip fear on
					} else {choice=2;} //else sleep
					//int randomnumber = 0, rmax = 4, rmin = 1;
					//Random random = new Random();
					//randomnumber = random.nextInt(rmax - rmin)+ rmin;
					//choice = randomnumber;
					System.out.println("***************************************");
					System.out.println(owner.getName() + " Choice : " + choice);
					System.out.println("***************************************");
					idle = false; //we are now busy with a new jerb!
					/** TODO add coordinate finder and setter to put purposeful travel in place, can later drive the coordinates from AI decision */
				}
				if (this.id.equals("banditAi")) {
					/** Bandit Rules
					 * If I have slept lasttime (bool) then
					 * 	else sleep
					 * If I don't see a farmer/farm then
					 *  TODO (needs thought) else kill
					 * wander aimlessly (bandits are a wandering threat)
					 */
					//TODO Bandits should sleep when aggression counter <50%
					this.choice =4; //test stub
					idle = false; //we are now busy with a new jerb!
				}
				if (this.id.equals("farmai")) {
					/** Farm Rules
					 * If there are four farms in a radius
					 * 	build a town nearby
					 * else sleep
					 */
					this.choice =4; //test stub
					idle = false; //we are now busy with a new jerb!
				}
			}
			switch (choice) {
				case 1: choice=1; //1 - Move (implies an attack if bandit, run if farmer)
				/** Strategy to implement 
				 * 1. Need to Generate a target position
				 * 2. Need to get there slowly, adding to x & y in small amounts, alternatingly until postion reached
				 * 3. Use same approach as farm and sleep below where task is set in one loop, and slowly worked towards in a hundred loop iterations,etc
				 **/
					// To accomplish directive #1, plug in instance method NpcDecideDirection() to instance method goForward below, pass in fear for farmer(drives purposeful running or random movement)
					MovementAiComponent.goForward(NpcDecideDirection(fear), owner.getRotation(),delta); //move via goForward method
					// should be testing if we hit target & breaking with a flag, if not move again next time
					System.out.println(owner.getName() + " moved"); //notify us again
					idle = true; //finished walking, get work
					tired = true; //finished walking, tired
				break;
				case 2: choice=2; //2 - Stay "sleep"
					sleeptimer ++;
					if (sleeptimer <= 1) {System.out.println(owner.getName() + " sleeping, timer: "+ sleeptimer);} //Don't spam us with notices
					if (sleeptimer >= 30) { //entity locked into path via counter, only when we loop through on xth time do we drop out via bool flip
						idle = true; sleeptimer=0; //success, reset idle flag and timer for next time
						System.out.println(owner.getName() + " Awake!");
						tired = false; //set entity well-rested, eligible for work
					} 
		      	break;
				case 3: choice=3; //3 - Build farm
					buildtimer ++;
					if (buildtimer <= 1) {System.out.println(owner.getName() + " building a farm, timer: "+buildtimer);} //Don't spam us with notices
					if (buildtimer >= 30) { //entity locked into path via counter, only when we loop through on xth time do we drop out via bool flip
						idle = true; buildtimer = 0; //success, reset idle flag and timer for next time
						System.out.println(owner.getName() + " Farm Finished!");
						tired = true; //finished working, tired
					} 
				break;
				case 4: choice=4;
				//do nothing, stub action
				break;
			}
		}
/** NPC Tools */
	    public boolean farmerRisk() {  //check for bandits near to farmer, use for farmer running or bandit hunting
	    	//Loop through bandit register to check for dangerclose
	    	if (CollisionComponent.checkDangerCloseBandits(owner.getPosition(), 60)) {
	    		return true;
	    	}
	    	else {return false;}
	    }
	    public Vector2f NpcDecideDirection(boolean fear) {
	    	/** Build this AI portion out for farmers 
	    	 * Currently they just wander aimlessly as each loop gives them a new random direction (counterproductive)
	    	 * if !fear, pick a random direction, more generic wandering for mobile entity
	    	 * if passing fear=true, assume farmer should idenify nearest bandit and beeline in opposite direction
	    	 * */
	    	float newpositionXf;float newpositionYf;float currentpositionXf;float currentpositionYf;
	    	Vector2f position = owner.getPosition();  //Get position to do basic map boundry checking
	    	if (!fear) { //if !fear, pick a random direction, more generic wandering for mobile entity
	    		int randomnumber = 0;
	    		Random random = new Random();
	    		int xmax = 760; int xmin = 20; //x range
	    		randomnumber = random.nextInt(xmax - xmin) + xmin; //randomly pick x
	    		newpositionXf=randomnumber;
	    		int ymax = 540;	int ymin = 70; //y range 
	    		randomnumber = random.nextInt(ymax - ymin) + ymin; //randomly pick y
	    		newpositionYf=randomnumber;
	    	} else { // fear=true 
	    		Vector2f badposition = CollisionComponent.getNearestBandit(owner.getPosition(), 60);
	    		newpositionXf = (badposition.getX())+15;newpositionYf = (badposition.getX())+15;  //anywhere but the bandits location is a good plac to run to, can TODO beef this up later
	    		System.out.println(owner.getName()+" running");
	    	}
			System.out.println(owner.getName() + " moving now to: " + newpositionXf+","+newpositionYf);  //notify us, target set
			//System.out.println(owner.getName() + " moving now to: " + owner.getPosition());  //notify us
			//Get there slowly, in small increments (2)
			currentpositionXf = position.getX(); currentpositionYf = position.getY(); //get current x & y as we move closer
			//need to kick off position analysis by if'ng whether new pos is less or greater than current for x, rinselathrep for y
			if ((currentpositionXf > newpositionXf) || (currentpositionXf < newpositionXf)) { //skip past if equals and work is done 
				if (currentpositionXf > newpositionXf) {
					currentpositionXf = currentpositionXf - 3;
				} else currentpositionXf = currentpositionXf + 3;
			}
			if ((currentpositionYf > newpositionYf) || (currentpositionYf < newpositionYf)) { //skip past if equals and work is done 
				if (currentpositionYf > newpositionYf) {
					currentpositionYf = currentpositionYf - 3;
				} else currentpositionYf = currentpositionYf + 3;
			}
			// Set new pos as we exit method
			position.x = currentpositionXf;
			position.y = currentpositionYf;
			return position;
	    }
}
