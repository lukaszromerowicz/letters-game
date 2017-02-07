package GameAssets;

public class LandscapePlatform extends Platform{
	public LandscapePlatform(double posX, double posY){
		super(posX, posY);
		this.getStyleClass().add("landscape-platform");
	}
}

