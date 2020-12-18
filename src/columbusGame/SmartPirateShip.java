package columbusGame;

import java.awt.Point;
import java.util.Random;

public class SmartPirateShip extends Ship{
	
	public SmartPirateShip() {
		this.currentLocation=OceanMap.getInstance().placeNpc();
		this.hop=3;
		rand=new Random();
	}
	@Override
	public String getImageD() {
		return "/resources/ship2.png";
	}
	
	@Override
	public void changeEventReceived(Player CCShip) {
		CCLocation=CCShip.getCurrentLocation();
		//System.out.printf("pirate moving to (%d,%d)!\n",CCLocation.x,CCLocation.y);
		move();
	}
	
	//TODO --- Improve Intelligence of SMart Pirate -- too dumb
	@Override
	public void move() {
		// TODO Auto-generated method stub
		 if(rand.nextInt(getHop()+1)==1){ //Slow down the Pirates
	
			 //pirates can move both x and y coordinate each move
			 //pirates cant pass through islands	
			 differenceX=getX()-CCLocation.x;
			 differenceY=getY()-CCLocation.y;
			 //if currentlocation below CCLocation enter loop ("this" is further to the left)
			 if (differenceX < 0 && OceanMap.getInstance().canMove(currentLocation.x+getHop(),currentLocation.y))
				 {
				 if(differenceX>-getHop() && OceanMap.getInstance().canMove(currentLocation.x+1,currentLocation.y))setX(getX()+1);
				 else setX(getX()+getHop());
				 }
			 //if CurrentLocation greater than CCLocation ("this" is further to the right)
			 else if (differenceX > 0 && OceanMap.getInstance().canMove(currentLocation.x-getHop(),currentLocation.y))
				 {
				 if(differenceX<getHop() && OceanMap.getInstance().canMove(currentLocation.x-1,currentLocation.y))setX(getX()-1);
				 else setX(getX()-getHop());
				 }
				 
			 //("this" is further above)
			 if (differenceY < 0 && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y+getHop()))
				 {
				 if(differenceY<-getHop() && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y+1))setY(getY()+1);
				 else setY(getY()+getHop());
				 }
			 //(this is below)
			 else if (differenceY > 0 && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y-getHop()))
			 	{
				 if(differenceY<getHop() && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y-1))setY(getY()-1);
				 else setY(getY()-getHop());
			 	}
		 }

	}
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 4;
	}
}
