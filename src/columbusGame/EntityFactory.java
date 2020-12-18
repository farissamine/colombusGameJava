package columbusGame;

public class EntityFactory{
	/*
	 * 0: Treasure
	 * 1: PowerUpSprite
	 * 2: CCShip
	 * 3: PirateShip
	 * 4: SmartPirateShip
	 * 5: ExpandingMonster
	 * 6: VerticalMonster
	 * 7: Monster
	 */
	
	//used for creation of Npc's on game board --things that change with difficulty of game.
	//Must be entity not Npc returned because Monster().class extends Entity 
	public Entity createEntity (int type) {
		if(type==0) {return new Treasure();}
		else if (type==1) {return new PowerUpSprite();}
		else if (type==2) {return new CCShip();}
		else if (type==3) {return new PirateShip();}
		else if (type==4) {return new SmartPirateShip();}
		else if (type==7) {return new Monster();}
		

		else return null;
	}


	
}
