package columbusGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IslandMonster extends Monsters{
	int xDimension;
	int yDimension;
	List<Point>hitboxList=new ArrayList<Point>();
	
	public IslandMonster() {
		this.currentLocation=OceanMap.getInstance().placeMonsterIsland();
		updateHitbox();
		rand=new Random();
		xDimension=OceanMap.dimensionsX;
		yDimension=OceanMap.dimensionsY;
		
	}
	
	@Override
	public void move() {
		//before trying island hop, check for CCShip
		canHug();
		
		//island hopping
		/*
		if(rand.nextInt(6)==1) {
			int x=rand.nextInt(xDimension);
			int y=rand.nextInt(yDimension);
			if(!(OceanMap.getInstance().canMove(x, y))) {
				this.setX(x);
				this.setY(y);
				updateHitbox();
			}
		}*/
	}
	
	private void canHug() {
		//System.out.println("Hug?");
		try {
		// TODO Auto-generated method stub
		for(Point hitbox:hitboxList) {
			waiting(hitbox);
		}}
		catch(Exception e) {
			
		}
		
	}

	private void updateHitbox() {
		hitboxList.clear();
		hitboxList.add(new Point(currentLocation.x+1,currentLocation.y));
		hitboxList.add(new Point(currentLocation.x+1,currentLocation.y+1));
		hitboxList.add(new Point(currentLocation.x,currentLocation.y+1));
		hitboxList.add(new Point(currentLocation.x,currentLocation.y-1));
		hitboxList.add(new Point(currentLocation.x-1,currentLocation.y-1));
		hitboxList.add(new Point(currentLocation.x-1,currentLocation.y));
		hitboxList.add(new Point(currentLocation.x-1,currentLocation.y+1));
		hitboxList.add(new Point(currentLocation.x+1,currentLocation.y-1));

	}

	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		return "/resources/IslandMonster.png";
	}

}
