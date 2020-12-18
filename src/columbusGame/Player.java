package columbusGame;

import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.image.ImageView;

public abstract class Player extends Entity{
	//CC is the only player
	CopyOnWriteArrayList<Npc> listeners;
	int lives;
	boolean movable;
	String description;
	
	public Player() {
		this.listeners = new CopyOnWriteArrayList<Npc>();
		this.lives=1;
		this.hop=1;
		this.movable=true;		
		this.description="unknown Player";//"You are Christopher Columbus. You are able to hop " +getHop()+ " spaces per turn and have a total of "+ getLives() +" lives remaining.";

	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 2;
	}
	public String getDescription() {
		return this.description;
	}
	//Implemented Observable methods, for Players
	  public void addMyChangeListener(Npc l) {
		    this.listeners.add(l);
	  }

	  public void removeMyChangeListener(Npc l) {
		    this.listeners.remove(l);
	  }
	  // Event firing method.  Called internally by other class methods.
	  //pass location (or all information needed by NPC's) of CCShip
	  protected void fireChangeEvent(Player CCShip) {
		  for (Npc l : listeners) {
		      l.changeEventReceived(CCShip);
		    }
	  }

	public int getLives() {
		return this.lives;
	}
	public CopyOnWriteArrayList<Npc> getListenerList() {
		return this.listeners;
	}
	//decrements lives of player by 1
	public void setLostLife() {
		this.lives-=1;
	}
	
	
	
	//functions called to move Player
	
	public void goNorth() {
		movable=OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y-getHop());
		//if in bounds of map and if space is neuatral (==0)
		if(movable) {
			setY(getY()-getHop());
			fireChangeEvent(this);
		}
	}
	public void goSouth() {
		boolean movable=false;
		movable=OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y+getHop());
		if(movable) { 
			setY(getY()+getHop());
			fireChangeEvent(this);
		}
	}
	public void goWest() {
		movable=OceanMap.getInstance().canMove(currentLocation.x-getHop(), currentLocation.y);
		if(movable) {
			setX(getX()-getHop());
			fireChangeEvent(this);
		}
	}
	public void goEast() {
		movable=OceanMap.getInstance().canMove(currentLocation.x+getHop(), currentLocation.y);
		if(movable) { 
			setX(getX()+getHop());
			fireChangeEvent(this);
		}
	}
	
	
	

}
