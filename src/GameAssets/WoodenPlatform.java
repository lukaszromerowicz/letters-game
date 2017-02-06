package GameAssets;

public class WoodenPlatform extends Platform{
	public WoodenPlatform(int startX, int endX, int level){
		super(startX, endX, level);
		this.getObject().getStyleClass().add("wooden-platform");
	}
}
