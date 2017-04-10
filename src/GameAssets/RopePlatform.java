package GameAssets;

/*
 * Rope platform class
 */
public class RopePlatform extends Platform{

	public RopePlatform(double posX, double posY) {
		super(posX, posY);
		this.getStyleClass().add("platform-rope");
	}

}
