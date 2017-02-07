package GameAssets;

public class Cloud extends GameObject{

	public Cloud(double x, double y) {
		super(x, y);
		this.getStyleClass().add("cloud");
		this.setFitHeight(35);
		this.setFitWidth(64);
	}

}
