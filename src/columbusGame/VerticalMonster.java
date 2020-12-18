package columbusGame;

import java.util.Random;

public class VerticalMonster extends Monsters{
	int moveInt;
	int xMove;
	
	
	//Moves horizontal along the y axis
	public VerticalMonster(){
		this.currentLocation=OceanMap.getInstance().placeNpc();
		rand=new Random();
		this.moveInt=1;
	}
	
	@Override
	public void move() {
		waiting(currentLocation);
		// Move X
		if(!caught) {
		xMove = this.getX()+moveInt;
		if (OceanMap.getInstance().canMove(xMove, getY())) {
			this.setX(xMove);
			}
		else {
			moveInt*=-1;
			xMove += moveInt;
			this.setX(xMove);

			}
		}
	}

	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		return "/resources/VerticalMonster.png";
	}


}
