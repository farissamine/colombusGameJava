package columbusGame;

public abstract class PowerUpDecorator extends Player{
	
		public abstract int getHop();
		public abstract String getDescription();
		public abstract int getLives();
		
		
		//Move function - powerup (move half of hops)
		public void goNorth(int subHop) {
			movable=OceanMap.getInstance().canMove(currentLocation.x,currentLocation.y-getHop()+subHop);
			//if in bounds of map and if space is neuatral (==0)
			if(movable) {
				setY(getY()-getHop()+subHop);
				fireChangeEvent(this);
			}
		}
		public void goSouth(int subHop) {
			movable=OceanMap.getInstance().canMove(currentLocation.x, currentLocation.y+getHop()-subHop);
			if(movable) { 
				setY(getY()+getHop()-subHop);
				fireChangeEvent(this);
			}
		}
		public void goWest(int subHop) {
			movable=OceanMap.getInstance().canMove(currentLocation.x-getHop()+subHop, currentLocation.y);
			if(movable) {
				setX(getX()-getHop()+subHop);
				fireChangeEvent(this);
			}
		}
		public void goEast(int subHop) {
			movable=OceanMap.getInstance().canMove(currentLocation.x+getHop()-subHop, currentLocation.y);
			if(movable) { 
				setX(getX()+getHop()-subHop);
				fireChangeEvent(this);
			}
		}
}
