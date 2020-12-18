package columbusGame;


public class Treasure extends GamePiece{
	
	public Treasure() {
		currentLocation=OceanMap.getInstance().placeEntity(this.getType());
		//this.addToPane(nodeList);
	}
	
	public void newTreasure() {
		currentLocation=OceanMap.getInstance().placeEntity(this.getType());
		setPositionX(getX());
		setPositionY(getY());
	}
	
	@Override
	public String getImageD() {
		return "/resources/x-spot.png";
	}


}
