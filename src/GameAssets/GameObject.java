package GameAssets;

import javafx.scene.image.ImageView;

public abstract class GameObject extends ImageView{
	
	public GameObject(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}
}
