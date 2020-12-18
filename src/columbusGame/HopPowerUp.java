package columbusGame;

import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;

public class HopPowerUp extends PowerUpDecorator{
	Player player;
	
	//powerUpType # == 0
	public HopPowerUp(Player player) {
		this.player=player;
		this.listeners = player.getListenerList();
		this.currentLocation=new Point();
		currentLocation.x=player.getX();
		currentLocation.y=player.getY();
		this.hop=1;
		this.lives=0;
		this.image=player.getImage();
		movable=true;
		this.description="You have aquired the HopPowerUp, this increases your hop stat by +1. To move 1 space Just hold shift while moving.";

	}

	@Override
	public int getHop() {
		return player.getHop()+this.hop;
		
	}
	@Override
	public String getImageD() {
		return "/resources/ship.png";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.description+player.getDescription();
	}

	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		return this.lives + player.getLives();
	}



}
