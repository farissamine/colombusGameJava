package columbusGame;

import java.util.Random;

public class BasicMonster extends Monsters {
	int moveInt;
	int xMove;
	
	public BasicMonster(){
		this.currentLocation=OceanMap.getInstance().placeNpc();
		rand=new Random();
		this.moveInt=1;
	}

	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		//return "columbusGame//ExpandingMonster.png";
		return "/resources/ExpandingMonster.png";
		
	}

}
