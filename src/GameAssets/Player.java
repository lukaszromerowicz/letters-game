package GameAssets;

import javafx.scene.shape.Rectangle;

public class Player extends GameObject{
	public Player(){
		object = new Rectangle(50,50);
		object.setTranslateX(10);
		object.setTranslateY(500);
	}
	
	public void moveX(int value){
		double oldVal = object.getTranslateX();
		object.setTranslateX(oldVal+value);
	}
}
