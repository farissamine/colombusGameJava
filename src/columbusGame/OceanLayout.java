package columbusGame;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanLayout extends Application{
	Pane root, menu;
	Scene scene1,scene2;
	Button rootbtn, easybtn,normalbtn,hardbtn;
	Label rootlbl, menulbl;
	Stage thestage;
	
	
	int dimensionsX,dimensionsY;
	final int scale;
	int turnCount=0;
	int treasures;
	
	int difficulty;
	
	//all entities (ships) except CC
	List<Npc> npcList;

	boolean isCaught;
	boolean poweredUp;
	PowerUpDecorator powerUp;

	//Entities involved in every game and not created in factory
	Player ship;
	PowerUpSprite powerUpSprite;
	Thread monsterThread;
	Treasure treasure;
	
	
	LevelFactory levelFactory;
	LevelEntity level;
	
	
	public OceanLayout() {
		//setting global variables necessary and setting scenes
		
		this.dimensionsX=OceanMap.getInstance().getDimensionX();
		this.dimensionsY=OceanMap.getInstance().getDimensionY();
		this.scale=OceanMap.getInstance().getScale();
		
		root=new AnchorPane();
		menu=new FlowPane();
		
		rootbtn=new Button("Click to go to the menu Scene");
		easybtn=new Button("Easy game");
		normalbtn=new Button("Normal game");
		hardbtn=new Button("Hard game");

		rootlbl=new Label("Game Scene");
		menulbl=new Label("Menu Scene");
		
		rootbtn.setLayoutX(0);
		rootbtn.setLayoutY(dimensionsY*scale);

		root.getChildren().addAll(rootlbl,rootbtn);
		menu.getChildren().addAll(menulbl,easybtn,normalbtn,hardbtn);
		
		
		//add rules/Instructions/ game mechanics menu to Main menu --
		scene2=new Scene(menu,200,200); //MainMenu Scene
		//scene is determined based off of dimensions and scale variables in OceanMap class
		scene1 = new Scene(root,dimensionsX*scale,dimensionsY*scale+26); // Game Scene (26 == btn size)
		
	
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void buildLevel(int difficulty) {
		
		//reset/ initialize npc
		npcList=new ArrayList<Npc>();
		
		//on difficulty button press set appropriate difficulty facotry
		levelFactory=new ConcreteLevelFactory();
		
		//creates games npc's and adds them to entity list
		level = levelFactory.createLevel(difficulty);

		//handle things not taken care of in factory
		//create thread for Monster class and create CCSHip
		for(Entity e:level.entitiesList) {
			if(e.getType()==7) {
				monsterThread=new Thread((Monster)e);
			}
		}
		
		//each game has a treasure, a player (ship), and a powerUpSprite
		ship= new CCShip();
		treasure=new Treasure();
		powerUpSprite=new PowerUpSprite();
		level.entitiesList.add(powerUpSprite);
		level.entitiesList.add(treasure);
		level.entitiesList.add(ship);
		
		//creates NPC list
		addToNpcList();
		
		//adds all Ship and Monsters npc's to CCShips listener list
		for(Npc n:npcList) {
			if(n.getClass().getSuperclass()==Ship.class || n.getClass().getSuperclass()==Monsters.class)
				ship.addMyChangeListener((Npc)n);
		}

		
		printGrid(OceanMap.getInstance().getMap());
		//creates gui based off of oceanMap grid
		drawMap();
		
		//adds all entities to pane, sets initial locations on board
		for(Entity e: level.entitiesList) {
			e.addToPane(root.getChildren());
			}
		
		//System.out.println(level.entitiesList.toString());
		//System.out.println(npcList.toString());
		}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		thestage=primaryStage;
		rootbtn.setOnAction(e-> ButtonClicked(e));
		easybtn.setOnAction(e-> ButtonClicked(e));
		normalbtn.setOnAction(e-> ButtonClicked(e));
		hardbtn.setOnAction(e-> ButtonClicked(e));

		//reset # of treasures collected to 0
		treasures=0;
		poweredUp=false;
		isCaught=false;
		
		
		thestage.setScene(getMenuScene());
		primaryStage.setTitle("Christopher Columbus Sails The Ocean Blue");
		primaryStage.show();
		startSailing();
			
	} 
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop(){
		monsterThread.stop();
	}
		
	//root and menu button actionEVENT
	private void ButtonClicked(ActionEvent e) {
		if(e.getSource()==rootbtn) {stop();thestage.setScene(scene2);}
		else {
			if(e.getSource()==easybtn)this.difficulty=1;
			if(e.getSource()==normalbtn)this.difficulty=2;
			if(e.getSource()==hardbtn)this.difficulty=3;
			
			//re-run start(stage) function
			newGame();
			buildLevel(this.difficulty);//when button on main menu pressed set difficulty according
			monsterThread.start();
			
			//switch scene to game scene
			thestage.setScene(scene1);
		}
	}
	
	//adds all Npc's to observer list, to be added to CC's Listener list
	private void addToNpcList() {
		for(Entity p:level.entitiesList) {
			if(p.getClass().getSuperclass().getSuperclass()==Npc.class) {
				System.out.println("Observer: "+p);
				npcList.add((Npc)p);
			}
			//adds monsters to NPC List
			else if(p.getClass()==Monster.class) {
				for(Monsters m:((Monster)p).getMonstersList()) {
					System.out.println("Observer: "+m);
					npcList.add((Npc)m);
				}
				
			}
		}		
	}
	
	//reset Singletons and re run start method, for a new game
	public void newGame() {
		OceanMap.getInstance().reset();
		try {
			start(thestage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//prints islandMap, grid.
	public void printGrid(int [] [] grid) {
		for(int i = 0; i < dimensionsY; i++)
		   {
		      for(int j = 0; j < dimensionsX; j++)
		      {
		         System.out.printf("%s ", grid[j][i]);
		      }
		      System.out.println();
		   }
		System.out.println("-----------------------------------------------------------");
	}

	
	//builds the gui for game based off of island map grid, defined in OceanMap class
	public void drawMap() {
		//draws initital map with ocean squares and islands based
		for(int x = 0; x < dimensionsX; x++){
		for(int y = 0; y < dimensionsY; y++){
			Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
			rect.setStroke(Color.GREY); // We want the black outline
			if(OceanMap.getInstance().getMap()[x][y]==1) {
				//Image islandImage = new Image("columbusGame//island.jpg",scale,scale,true,true);
				
				Image islandImage;
				try{
					islandImage = new Image(OceanLayout.class.getResourceAsStream("/resources/island.jpg"), scale, scale, true, true);
					//Image islandImage = new Image("columbusGame//resources//island.jpg",scale,scale,true,true);
					//creates and adds an island image to the root
					rect.setFill(new ImagePattern(islandImage)); 
				}catch(Exception e) {
					System.out.println("Amine got an exception: " + e);
				}
				
			}
			else {
				//Neutral squares or ocean == 0
				rect.setFill(Color.PALETURQUOISE); 

			}
			root.getChildren().add(rect); // Add to the node tree in the pane
		}
		}
	}
	//movement function
	private void startSailing(){
		 scene1.setOnKeyPressed(new EventHandler<KeyEvent>(){
		 @Override
		 public void handle(KeyEvent ke) {
			//System.out.println(ke.getCode());
			if(ship.getLives()>0) {
				//if you press shift then move, you move 1 space, regardless of hop stat, if poweredUp
				if(ke.isShiftDown()&&poweredUp) {
					//wont change movement if not poweredUp, max hop==2
					int subHop=(ship.getHop()-ship.getHop()+1);
					switch(ke.getCode()){
					case D:
					((PowerUpDecorator)ship).goEast(subHop);
					break;
					case A:
					((PowerUpDecorator)ship).goWest(subHop);
					break;
					case W:
					((PowerUpDecorator)ship).goNorth(subHop);
					break;
					case S:
					((PowerUpDecorator)ship).goSouth(subHop);
					break;
					default:
					break;
					}
				}
				else {
					switch(ke.getCode()){
					case D:
					ship.goEast();
					break;
					case A:
					ship.goWest();
					break;
					case W:
					ship.goNorth();
					break;
					case S:
					ship.goSouth();
					break;
					default:
					break;
					}
				}
			}
			else {
				//if monsters kill CCShip ends up in this while CCShip tries moving whilst stuck
				loseGame();
			}
			turnCount++;

			//all gui images moved in their respective classes
			//checks for conditions based on CCLocation
			//Note: not able to update often enough for MonsterThread
			
			didWin();
			didLose();
			didPowerUp();
		
		 }

		 });
	}
	
	public void didPowerUp() {
		//powerUp Condition
		if(ship.getCurrentLocation().equals(powerUpSprite.getCurrentLocation())&&!poweredUp) {
			powerUp();
		}
	}

	public void setPowerUp(int type) {
		//1 random power up will be generated per OceanMap (powerUps include: Hop,Life,)
		if(type==0)ship=new HopPowerUp(ship);
		else if(type==1)ship = new LifePowerUp(ship);
		
	}
	public void didLose() {
		//lose condition, checks locations of all npc's against CCShip, after CCShip moves
		for(Npc o: npcList) {
			if(ship.getCurrentLocation().equals(o.getCurrentLocation())) {
				(ship).setLostLife();
				if(ship.getLives()<1)loseGame();
				else {
					o.setX(o.spawn.x);
					o.setY(o.spawn.y);
					//If you have a life remaining after being caught jmp here
						//send CCShip to random location on map?
					System.out.println("You have one life remaining, be careful!");

				}
			}
		}
		
		
	}
	public void didWin() {
	//Win condition
	if(ship.getCurrentLocation().equals(treasure.getCurrentLocation())) {
		treasures++;
		if(treasures==3) {
			winGame();//option to try again with increased difficulty after a win?
		}
		else {
			treasure.newTreasure();
		}
		
	}
	}
	
	public void powerUp() {
		poweredUp=true;
		
		//pause Monsters
		for(Entity e:level.entitiesList) {
			if(e.getType()==7) {
				((Monster)e).running=false;
			}
		}

		//removes powerUp from board
		root.getChildren().remove(powerUpSprite.getImage());
		
		//wraps ship in appropriate powerUp
		setPowerUp(OceanMap.getInstance().getPowerUpType());
		
		//provide alert dialogue to explain power up
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("You have picked up a power up");
		alert.setHeaderText(null);
		alert.setContentText(((Player)ship).getDescription());

		alert.showAndWait();
		System.out.println("POWER UP!");
		
		//resume Monsters
		for(Entity e:level.entitiesList) {
			if(e.getType()==7) {
				((Monster)e).running=true;

				}
			}	
		}

	public Scene getGameScene() {
		return scene1;
	}
	public Scene getMenuScene() {
		return scene2;
	}

	
	//lose game notification
	public void loseGame() {
		//stop monster thread
		stop();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("You Lose");
		alert.setHeaderText(null);
		alert.setContentText("You have lost the game and will now be redirected to the main menu. Nice try!");

		alert.showAndWait();
		thestage.setScene(getMenuScene());
	}
	//Win game notification
	public void winGame() {
		//stop monster thread
		stop();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("You Win");
		alert.setHeaderText(null);
		alert.setContentText("You have won the game and will now be redirected to the main menu. Nice job!");

		alert.showAndWait();
		thestage.setScene(getMenuScene());
	}

		

}
