package GameAssets;

public class LandscapeWater extends Platform{

	public LandscapeWater(double posX, double posY) {
		super(posX, posY);
		this.getStyleClass().add("water");
	}

}
