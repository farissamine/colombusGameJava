package test;

import org.junit.Test;
import columbusGame.*;
import static org.junit.Assert.*;

public class OceanMapTest {

	//Test the main method in Ocean Map "canMove" makes sure that it is
	//an illegal move to be out of the bonunds of the map
	@Test
	public void testCanMove() {
		
		Player CCShip=new CCShip();
		int mapx=OceanMap.getInstance().getDimensionX();
		int mapy=OceanMap.getInstance().getDimensionY();

		CCShip.getCurrentLocation().x=-1;
		boolean test1=!(OceanMap.getInstance().canMove(CCShip.getX(),CCShip.getY()));
		
		CCShip.getCurrentLocation().x=mapx+1;
		boolean test2=!(OceanMap.getInstance().canMove(CCShip.getX(),CCShip.getY()));
		
		CCShip.getCurrentLocation().y=mapy+1;
		boolean test3=!(OceanMap.getInstance().canMove(CCShip.getX(),CCShip.getY()));
		
		CCShip.getCurrentLocation().y=-1;
		boolean test4=!(OceanMap.getInstance().canMove(CCShip.getX(),CCShip.getY()));
		
		assertTrue(test1 && test2 && test3 && test4);
		
		
	}

}
