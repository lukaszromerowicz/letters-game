package GameAssets;

/*
 * Brick platform class
 */
public class BrickPlatform extends Platform{

	public BrickPlatform(double posX, double posY) {
		super(posX, posY);
		this.getStyleClass().add("brick-platform");
	}

}
