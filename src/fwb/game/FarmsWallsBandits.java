package fwb.game;


import org.newdawn.slick.AppGameContainer; 
import org.newdawn.slick.BasicGame; 
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image; 
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap; 

/** Light Sim-RTS 2D game, goal is to support population growth to 10 of either farmers or bandits */

public class FarmsWallsBandits extends BasicGame {
	
/** Graphics Work */
	Color LBColor = new Color(111,83,40); //light brown
	Color DBColor = new Color(65,41,3); //Dark brown
	private TiledMap farmMap;  //Tile Map
	Image sbd1 = null; //Scoreboard Images
	Image sbd2 = null; //Scoreboard Images
	Image sbd3 = null; //Scoreboard Images
	Image sbd4 = null; //Scoreboard Images
	
/** Create Entities */	
    Entity h1 = null; //hero1
	Entity f1 =null; //farmer1
	Entity f2 =null; //farmer2
	Entity b1 =null; //bandit1
	Entity b2 =null; //bandit2
	Entity F1 =null; //farm1
	Entity F2 =null; //farm2
	Entity B1 = null; //banditcamp1
	
	// TODO Make a Farms objects, here, eventually convert to an array of entities that are added, updated, drawn, etc through an array loop 
	//String[] entArray = {"h1", "f1"};
			
	public FarmsWallsBandits() { //Constructor stub
		super("FWBGame");
	}

	@Override
	//init() - This is called when the game starts and should be used to load resources and initialise the game state.
	public void init(GameContainer gc) //Init objects, do other work 
		throws SlickException {			
	/** Scoreboard */
		GameUtil.setMessage("Welcome, Press arrows or w,a,s,d to move the hero");  //Starting Message
        farmMap = new TiledMap("res/farmMap1.tmx");
        sbd1 = new Image("res/plow.jpg");
        sbd2 = new Image("res/harrow.jpg");
        sbd3 = new Image("res/banditgang.jpg");
        sbd4 = new Image("res/workers.png");
        
    /** Entities need to support a 10 ct of each
     	* Hero
     	* Farmers 
     	* Farms 
     	* Bandit 
     	* Banditcamps 
     	* ..
      	*/
        h1 = new Entity("hero",0);
        h1.AddComponent( new ImageRenderComponent("walkerRender", new Image("res/hero.png")) );
        h1.AddComponent( new MovementComponent("HeroMovement") );
        f1 = new Entity("farmer",0);
		f1.AddComponent(new ImageRenderComponent("walkerRender", new Image("res/farmer.png")) );
		f1.AddComponent( new AiComponent("farmerAi"));
		f1.setName("farmer1");
		f2= new Entity("farmer",1);
		f2.AddComponent(new ImageRenderComponent("walkerRender", new Image("res/farmer.png")) );
		f2.AddComponent( new AiComponent("farmerAi"));
		f2.setName("farmer2");
		b1 = new Entity("bandit",0);
		b1.AddComponent(new ImageRenderComponent("walkerRender", new Image("res/bandit.png")) );
		b1.AddComponent( new AiComponent("banditAi"));
		b1.setName("bandit1");
		b2 = new Entity("bandit",1);
		b2.AddComponent(new ImageRenderComponent("walkerRender", new Image("res/bandit.png")) );
		b2.AddComponent( new AiComponent("banditAi"));
		b2.setName("bandit2");
		F1 = new Entity("farm",0);
		F1.AddComponent(new ImageRenderComponent("hutRender", new Image("res/farm.png")) );
		F2 = new Entity("farm",1);
		F2.AddComponent(new ImageRenderComponent("hutRender", new Image("res/farm.png")) );
		B1 = new Entity("banditcamp",0);
		B1.AddComponent(new ImageRenderComponent("hutRender", new Image("res/banditcamp.png")) );
	}

	@Override
	//update() - The method is called each game loop to cause your game to update its logic. So if you have a little guy flying across the screen, this is where you should make him move. This is also where you should check input and change the state of the game.
	public void update(GameContainer gc, int delta) throws SlickException {
   	    h1.update(gc, null, delta);
   	    b1.update(gc, null, delta);
   	    b2.update(gc, null, delta);
   	    f1.update(gc, null, delta);
   	    f2.update(gc, null, delta);
   	    F1.update(gc, null, delta);
   	    F2.update(gc, null, delta);
   	    B1.update(gc, null, delta);
	}

	@Override
	//render() - This method is passed a Graphics instance, which can be used to draw to the screen. All of your game's rendering should take place in this method (or in methods called from this method).
	public void render(GameContainer gc, Graphics gr) throws SlickException {
		// Draws objects, images at the specified positions
		//gr.setAntiAlias(true); //make em pretty pngs;
		//set vsync?
		gr.setColor(Color.black); //Text Color FPS gauge
	/** Tile Map Render */
		farmMap.render(0, 56);
	/** ScoreBoard Rendering */
		//Boxes
		gr.setColor(LBColor); //Box Fill color "Light Brown Custom"
		gr.fillRect(0, 1, 1022, 54); //Welcome box
		gr.drawImage(sbd1,10,1); //ScoreBoard Art 1
		gr.fillRect(800, 54, 222, 272);//Farmer box
		gr.drawImage(sbd2,807,62); //ScoreBoard Art 2
		gr.fillRect(800, 326, 222, 272);//Bandit box
		gr.drawImage(sbd3,810,334); //ScoreBoard Art 3
		gr.fillRect(0, 599, 1023, 167);//Hero box
		gr.drawImage(sbd4,10,607); //ScoreBoard Art 4
		//Borders
		gr.setColor(DBColor); //Box Border color "Dark Brown Custom"
		gr.drawRect(0, 0, 1023, 54); //WB border
		gr.drawRect(800, 55, 222, 272); //FB border
		gr.drawRect(800, 326, 222, 272); //BB border
		gr.drawRect(0, 599, 1022, 167); //HB border
		//Text
		gr.setColor(Color.white); //Text Color
		gr.drawString("FarmsWallsBandits",280,15); //WBT1
		gr.drawString("Farmers: 2",807,200); //FT1
		gr.drawString("Farms: 2",807,220); //FT2
		gr.drawString("Towns: 0",807,240); //FT3
		gr.drawString("Keeps: 0",807,260); //FT4
		gr.drawString("Knights: 0",807,280); //FT5
		gr.drawString("Bandits: 2",810,455); //BT1
		gr.drawString("BanditCamps: 1",810,475); //BT2
		gr.drawString("Killed: 0",810,495); //BT3
		gr.drawString("Burned: 0",810,515); //BT4
		gr.drawString("Aggression: 30%",810,535); //BT5
		gr.drawString("The Player",242,610); //HT1
		gr.drawString((GameUtil.getMessage()),242,630); //HT2, debug message for controller input
		gr.drawString("The game goal is to reach a population of 10 farmers or 10 bandits",242,650); //HT3
		gr.drawString("Health: 100/100",242,670); //HT4
		gr.drawString("Hunger Level: 25%",400,670); //HT4.5
		gr.drawString("Position: " + h1.getPosition(),242,690); //HT5
		gr.drawString("Collision: N",680,690); //HT5.5
		
	/** Entity Rendering */
		h1.render(gc, null, gr);
		f1.render(gc, null, gr);
		f2.render(gc, null, gr);
		b1.render(gc, null, gr);
		b2.render(gc, null, gr);
		F1.render(gc, null, gr);
		F2.render(gc, null, gr);
		B1.render(gc, null, gr);	
    }
    	
	//Lets rock
	public static void main(String[] args) {
	     try {
	    	  AppGameContainer app = new AppGameContainer(new FarmsWallsBandits());
	          	app.setDisplayMode(1024, 768, false);
	            app.start();
	     } catch (SlickException e) {
	          e.printStackTrace();
	     }
	}
}