package GameAssets;

public class WoodenPlatform extends Platform{
	public WoodenPlatform(double posX, double posY){
		super(posX, posY);
		this.getStyleClass().add("wooden-platform");
	}
}
