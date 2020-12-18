package test;

import org.junit.Test;
import columbusGame.*;
import static org.junit.Assert.*;


public class DecoratorTest {

	//Tests for PowerUpDecorator Class
	@Test
	public void testExtraLife() {
		Player CCShip=new CCShip();
		CCShip=new LifePowerUp(CCShip);
		CCShip.setLostLife();
		assertTrue(CCShip.getLives()==1);
		
	}
	
	@Test
	public void testExtraLife2() {
		Player CCShip=new CCShip();
		CCShip=new LifePowerUp(CCShip);
		CCShip.setLostLife();
		CCShip.setLostLife();
		assertTrue(CCShip.getLives()==0);
		
	}
	
	@Test
	public void testExtraHop() {
		Player CCShip=new CCShip();
		CCShip=new HopPowerUp(CCShip);
		assertTrue(CCShip.getHop()==2);
		
	}
}
