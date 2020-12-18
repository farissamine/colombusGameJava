package columbusGame;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.image.ImageView;


public class CCShip extends Player{
	boolean movable;
	int dimensions;
	
	public CCShip() {
		this.listeners = new CopyOnWriteArrayList<Npc>();
		this.currentLocation=new Point(0,0);
		//starts at 0,0 always
		this.description="You are Christopher Columbus. By default you are able to hop 1 space and have 1 life";
		movable=true;
	}
	
	@Override
	public String getImageD() {
		//return "columbusGame//ship.png";
		return "/resources/ship.png";
	}

	public void setImage(ImageView pic) {
		this.image=pic;
	}



}

