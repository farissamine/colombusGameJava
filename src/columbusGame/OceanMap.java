package columbusGame;

import java.awt.Point;
import java.util.Optional;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;

public class OceanMap {
	private static OceanMap uniqueInstance;
	static int [] [] map;
	Random rand;
	int v,w;
	Point pirate1,pirate2,entityLocation, powerUpLocation;
	//60 x -- 30 y == default
	static int dimensionsX = 60;
	static int dimensionsY = 30;
	static int scale=20;
	//15pct of map squares are island squares
	static int islandCount=(int) (dimensionsX*dimensionsY*.15);
	int numberOfPowerUps=2;
	
	
	/*/Map numbers
	
	*1: Island
	*2: Pirate Island/Pirate spawn
	*3: Treasure
	*4: PowerUp -- 1: HopPowerUp, 2: LifePowerUp
	*5: Monster
	*/
	
	
	private OceanMap() {
		map=new int [dimensionsX] [dimensionsY];
		//sets all elements to neutral spaces when constructed
		for(int i=0; i<dimensionsX;i++) {
			for(int j=0;j<dimensionsY;j++) {
			map[i][j]=0;
		}
		}
		rand = new Random();
		
		//setting map, places islandCount islands (Generates x and y coordinates and sets according grid location to 1
		placeIslands();
	}

	public void reset() {
	    uniqueInstance = new OceanMap();
	}
	
	public static OceanMap getInstance() {
		if(uniqueInstance==null) {
			uniqueInstance=new OceanMap();
		}
		return uniqueInstance;
	}
	
	
	
	//checks if parameter passed point is passable
	public boolean canMove(int px,int py) {
		//if within dimensions of map
		if(px<dimensionsX && px>-1 && py>-1 && py<dimensionsY)
		{
			//if a neutral sqaure or the treasure can move or a powerUp
			if(map[px][py]==0 || map[px][py]==3)return true;
		}
		return false;
	}
	
	
	//sets randoms islandCount spots on map to 1 for islands
	private void placeIslands() {
		int islandsToPlace=islandCount;
		while(islandsToPlace>0) {
			int x=rand.nextInt(dimensionsX);
			int y=rand.nextInt(dimensionsY);
			
			//islands cant be on origin
			//if int not within safe zone  and neutral spot place island
			if(!safeZone(x,y)&&map[x][y]==0) {
				map[x][y]=1;
				islandsToPlace--;
			}
		}

	}
	//generates point for GamePieces (i.e. Treasure & PowerUp), sets map according
	public Point placeEntity(int type) {
		boolean entityPlaced=false;
		while(!entityPlaced) {
			int x=rand.nextInt(dimensionsX);
			int y=rand.nextInt(dimensionsY);
			if(!treasureSafeZone(x,y) && map[x][y]==0) {
				map[x][y]=type;
				entityPlaced=true;
				entityLocation=new Point(x,y);
				//location of entity 
				/*System.out.printf("x: %d ",x);
				System.out.printf("y: %d\n",y);*/
			}
		}
		return entityLocation;
	}
	public int getPowerUpType() {
		int x = rand.nextInt(numberOfPowerUps);
		return x;
	}
	
	//creates and returns new Point, for Npc's
	public Point placeNpc() {
		boolean npcPlaced=false;
		while(!npcPlaced) {
			v=rand.nextInt(dimensionsX);
			w=rand.nextInt(dimensionsY);
			if(!safeZone(v,w)&&map[v][w]==0) {
				npcPlaced=true;	
			}
		}
		return new Point(v,w);
	}
	//generates point for IslandMonster, creates island in that location
		public Point placeMonsterIsland() {
			boolean entityPlaced=false;
			while(!entityPlaced) {
				int x=rand.nextInt(dimensionsX);
				int y=rand.nextInt(dimensionsY);
				if(!treasureSafeZone(x,y) && map[x][y]==0) {
					map[x][y]=1;
					entityPlaced=true;
					entityLocation=new Point(x,y);
					//location of entity 
					/*System.out.printf("x: %d ",x);
					System.out.printf("y: %d\n",y);*/
				}
			}
			return entityLocation;
		}
	
	public boolean treasureSafeZone(int x , int y) {
		Point check=new Point(x,y);
		if(check.distance(0,0)<dimensionsX/2) {
			//in safe zone, re randomize treasure x and y
			return true;
		}
		//distance of treasure from PLayer CC
		//System.out.println(check.distance(0, 0));
		
		return false;
	}
	
	public boolean safeZone(int x, int y) {
		Point check=new Point(x,y);
		//System.out.println(check.distance(0,0));
		if(check.distance(0, 0)<5) {
			return true;
		}
		return false;
	}
	
	 // Return generated map
	public int[][] getMap(){
		return map; 
	 }
	public int getScale() {
		return scale;
	}
	public int getDimensionX() {
		return dimensionsX;
	}
	public int getDimensionY() {
		return dimensionsY;
	}



	

}

