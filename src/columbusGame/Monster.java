package columbusGame;

import java.awt.Point;
import java.util.Arrays;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Monster extends Entity implements Runnable {
	int monsterCount=20;
	Boolean running = true;
	int monsterTypes=3; //Basic, Vertical, Island
	int scale=OceanMap.getInstance().getScale();
	Monsters[] monsters = new Monsters[monsterCount];
	
	public Monster(){
		for(int j = 0; j < monsterCount; j++){
			//mod by number of monsters (MonsterTypes)
			if(j%monsterTypes==0)monsters[j] = new VerticalMonster();
			if(j%monsterTypes==1)monsters[j] = new BasicMonster();
			if(j%monsterTypes==2)monsters[j] = new IslandMonster();

		}
	}

	public Monsters[] getMonstersList() {
		//System.out.println(monsters.toString());
		return monsters;
	}

	@Override
	public void addToPane(ObservableList<Node> sceneGraph){
		for(Entity monster: monsters){
			Image entityImage = new Image( OceanLayout.class.getResourceAsStream(monster.getImageD()),scale,scale,true,true);
			ImageView entityImageView = new ImageView(entityImage);
			monster.setImage(entityImageView);
			monster.setPositionX(monster.getX());
			monster.setPositionY(monster.getY());
			monster.spawn = new Point(monster.getX(), monster.getY());
			System.out.println("Adding entity to pane: " + monster.getX() + ", " + monster.getY());
			sceneGraph.add(entityImageView);
			
		}
	}
			
	@Override
    public void run() {
		while (true) {
		    	try {
				Thread.sleep(500);
				} 
		    	catch (InterruptedException e) {
				e.printStackTrace();
				}
		    	
		    	for(Monsters monsterSprite: monsters){
		    		if(running) {
		    			//System.out.println("Running");
		    			monsterSprite.move();
		    			//notify CCShip of updated move
		    			}
		    		}
      }
      
    }

	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 7;
	}



}
