package columbusGame;

import java.awt.Point;

public class LifePowerUp extends PowerUpDecorator{
	Player player;
	//powerUpType==1
	public LifePowerUp(Player player) {
		this.player=player;
		this.description="You have aquired the LifePowerUp, this increases your lives stat by +1.";
		this.listeners = player.getListenerList();
		this.currentLocation=new Point();
		currentLocation.x=player.getX();//starts at 0,0 always
		currentLocation.y=player.getY();
		this.hop=0;
		this.lives=1;
		this.image=player.getImage();
		movable=true;		
		//setPositionX(getX());
		//setPositionY(getY());
	}
	@Override
	public int getLives() {
		// TODO Auto-generated method stub
		//System.out.printf("playerLives: %d, Dec.PlayerLives: %d\n", player.getLives(),this.lives);
		return player.getLives()+this.lives;
	}
	
	/*
	@Override
	public int powerUp() {
		// TODO Auto-generated method stub
		return 1+player.powerUp();
	}
	
	@Override
	public int getPowerUpType() {
		// TODO Auto-generated method stub
		return powerUpType;
	}
	 */
	
	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		return "/resources/ship.png";
	}
	@Override
	public int getHop() {
		// TODO Auto-generated method stub
		return this.hop + player.getHop();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return  this.description+player.getDescription();
	}

}
