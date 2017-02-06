package GameAssets;

public class LandscapePlatform extends Platform{
	public LandscapePlatform(int startX, int endX, int level){
		super(startX, endX, level);
		this.getObject().getStyleClass().add("landscape-platform");
	}
}
