package GameAssets;

import javafx.scene.shape.Rectangle;

public class Platform extends GameObject{
	public Platform(int startX, int endX, int level){
		object = new Rectangle(endX-startX,10);
		object.setTranslateX(startX);
		object.setTranslateY(level);
	}
}
