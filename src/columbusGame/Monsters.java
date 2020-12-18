package columbusGame;

import java.awt.Point;

public abstract class Monsters extends Npc {
	//abstract class for all monster types

	boolean caught=false;
	//Monsters need way of killing CCShip since operating on a different thread
	public void killCCShip() {
		this.CCShip.setLostLife();
	}
	
	//checks for lose condition for CCShip, if monsterlocation==CCLocation stick to CCShip 
	public void waiting(Point currentLocation) {
		try {
			if(CCLocation.equals(currentLocation)) {
				this.setX(CCLocation.x);
				this.setY(CCLocation.y);
				caught=true;
				killCCShip();
			}
		}
		catch(Exception x) {
			//occurs if CCShip has yet to move, then CCLocation=null
			//System.out.println("Error checking for CCShip");
		}

	}
	
	@Override
	public void move() {
		waiting(currentLocation);
		
		if(!caught) {
		// Move X
		int xMove = this.getX() + rand.nextInt(3)-1;
		if (OceanMap.getInstance().canMove(xMove, getY()))
			this.setX(xMove);
		// Move Y
		int yMove = this.getY() + rand.nextInt(3)-1;
		if (OceanMap.getInstance().canMove(getX(), yMove))
			this.setY(yMove);
	
		}
	}
	
	//know cc location
	@Override
	public void changeEventReceived(Player CCShip) {
		this.CCShip=CCShip;
		CCLocation=this.CCShip.getCurrentLocation();
	}
	
	
	@Override
	public int getType() {
		return 6;
	}
}
