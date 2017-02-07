package GameAssets;

public class LandscapeCenterPlatform extends Platform{

	public LandscapeCenterPlatform(double posX, double posY) {
		super(posX, posY);
		this.getStyleClass().add("platform-center");
	}

}
