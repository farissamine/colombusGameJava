package columbusGame;

import java.awt.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {

	//Entity entity;
	ImageView image;
	Point currentLocation;
	int scale=OceanMap.scale;
	int hop;
	Point spawn;
	
	//Abstract methods
	public abstract String getImageD();
	public abstract int getType();

																						
	//adds entity to pane and sets initial location for image on gui
	public void addToPane(ObservableList<Node> sceneGraph){
		Image entityImage = new Image(OceanLayout.class.getResourceAsStream(this.getImageD()),scale,scale,true,true);
		ImageView entityImageView = new ImageView(entityImage);
		this.setImage(entityImageView);
		this.setPositionX(getX());
		this.setPositionY(getY());
		spawn = new Point(getX(), getY());
		System.out.println("Adding " + this.getClass().toString() + " to pane: " + this.getX() + ", " + this.getY());
		sceneGraph.add(entityImageView);
	}
	
	public Point getCurrentLocation(){
		return currentLocation;
	}
	
	public int getX() {
		return currentLocation.x;
	}
	public int getY() {
		return currentLocation.y;
	}
	public void setX(int x) {
		this.currentLocation.x=x;
		setPositionX(x);
	}
	public void setY(int y) {
		this.currentLocation.y=y;
		setPositionY(y);
	}
	public void setPositionX(int x) {
		image.setX(x*scale);
	}
	public void setPositionY(int y) {
		image.setY(y*scale);
	}
	public ImageView getImage() {
		return this.image;
	}
	public void setImage(ImageView pic) {
		this.image=pic;
	}
	public int getHop() {
		return this.hop;
	}
	
}
