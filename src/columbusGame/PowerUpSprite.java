package columbusGame;

public class PowerUpSprite extends GamePiece{
	
	public PowerUpSprite() {
		currentLocation=OceanMap.getInstance().placeEntity(this.getType());
	}

	@Override
	public String getImageD() {
		// TODO Auto-generated method stub
		return "/resources/1upshroom.png";
	}

	
	

}
