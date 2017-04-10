package GameAssets;

/*
 * Live icon class
 */
public class LiveIcon extends GameObject{

	public LiveIcon(double x, double y) {
		super(x, y);
		this.setFitHeight(22);
		this.setFitWidth(26);
		this.getStyleClass().add("hud-live");
	}

	
}
