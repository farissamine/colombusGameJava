package columbusGame;

import java.util.Random;

public class PirateShip extends Ship{
	int stuckCount;
	final int stuckLimit=4;
	
	public PirateShip() {
		this.hop=1;
		rand=new Random();
		this.currentLocation=OceanMap.getInstance().placeNpc();
		stuckCount=0;
	}
	
	@Override
	public void changeEventReceived(Player CCShip) {
		CCLocation=CCShip.getCurrentLocation();
		//System.out.printf("pirate moving to (%d,%d)!\n",CCLocation.x,CCLocation.y);
		move();
		if(stuckCount>stuckLimit)unstuck();
	}
	
	public void move() {
		moved=false;
		differenceX=getX() - CCLocation.x;
		differenceY=getY() - CCLocation.y;
		// TODO Auto-generated method stub
		if(rand.nextInt(getHop()+1)==1){ //Slow down the Pirates, .5 chance of moving
			 //pirates can move either the x or the y coordinate each move
			 //pirates cant pass through islands
			 if(Math.abs(differenceX)>Math.abs(differenceY)) {
				 if (differenceX < 0 && OceanMap.getInstance().canMove(currentLocation.x+getHop(),currentLocation.y))
					 {setX(getX()+getHop());moved=true;}
				 else if (differenceX > 0 && OceanMap.getInstance().canMove(currentLocation.x-getHop(),currentLocation.y))
					 {setX(getX()-getHop());moved=true;}
			 }
			 else {
				 if (differenceY < 0 && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y+getHop()))
					 {setY(getY()+getHop());moved=true;}
				 else if (differenceY > 0 && OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y-getHop()))
					 {setY(getY()-getHop());moved=true;}
			 }
		}
		 else {/*slow down random int generation has successfully slowed pirate down*/}
		 if(moved==false) {stuckCount++;/*System.out.println("not Moving");*/}
	}

	
	//if a pirate ship is stuck somewhere for more than stucklimit turns they are able to jump that island
	private void unstuck() {
		System.out.println("Stuck!!!");
		int[][] islandMap=OceanMap.getInstance().getMap();
		//if pirate should move along y axis else move x axis
		try {
			//trying to skip over an island ---REDO
			//if columbus's y cordinate is further than his x cordinate from pirateShip enter if
			if(Math.abs(differenceY)>Math.abs(differenceX)) {
				if(islandMap[getX()][getY()+1]==1) {
					//if can move to island location +1
					if(OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y+getHop()+1)) {
						setY(currentLocation.y+getHop()+1);
						stuckCount=0;
						}
					}
				else if(islandMap[getX()][getY()-1]==1) {if(OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y-getHop()-1)) {setY(currentLocation.y-getHop()-1);stuckCount=0;}}
				else if(islandMap[getX()+1][getY()]==1) {if(OceanMap.getInstance().canMove(currentLocation.x+getHop()+1, currentLocation.y)) {setX(currentLocation.x+getHop()+1);stuckCount=0;}}
				else if(islandMap[getX()-1][getY()]==1) {if(OceanMap.getInstance().canMove(currentLocation.x-getHop()-1, currentLocation.y)) {setX(currentLocation.x-getHop()-1);stuckCount=0;}}
			}
			else {
				//check X locations 1st
				if(islandMap[getX()+1][getY()]==1) {if(OceanMap.getInstance().canMove(currentLocation.x+getHop()+1, currentLocation.y)) {setX(currentLocation.x+getHop()+1);stuckCount=0;}}
				else if(islandMap[getX()-1][getY()]==1) {if(OceanMap.getInstance().canMove(currentLocation.x-getHop()-1, currentLocation.y)) {setX(currentLocation.x-getHop()-1);stuckCount=0;}}
				else if(islandMap[getX()][getY()+1]==1) {if(OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y+getHop()+1)) {setY(currentLocation.y+getHop()+1);stuckCount=0;}}
				else if(islandMap[getX()][getY()-1]==1) {if(OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y-getHop()-1)) {setY(currentLocation.y-getHop()-1);stuckCount=0;}}

			}
		}
		catch(Exception e){
			//here to catch if there's an error when looking for where an island is
			System.out.println("Oops, not an island over there.");
		}
		//System.out.println(stuckCount);

	}
	
	@Override
	public String getImageD() {
		return "/resources/pirateShip.png";
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 3;
	}



}
