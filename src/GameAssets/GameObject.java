package GameAssets;

import javafx.scene.image.ImageView;

/*
 * Abstract class for game objects
 */
public abstract class GameObject extends ImageView{
	
	/*
	 * Constructor, sets object position
	 * 
	 * @param x Specifies object position in X axis.
	 * @param y Specifies object position in Y axis.
	 */
	public GameObject(double x, double y)
	{
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
