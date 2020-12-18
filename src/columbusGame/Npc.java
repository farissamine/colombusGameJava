package columbusGame;

import java.awt.Point;
import java.util.Random;

public abstract class Npc extends Entity{
	//observer
	Random rand;
	Point CCLocation;
	Player CCShip;
	
	  public abstract void changeEventReceived(Player CCship);
	  public abstract void move();

}
