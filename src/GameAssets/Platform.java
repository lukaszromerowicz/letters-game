package GameAssets;

/*
 * Platform object class
 */
public abstract class Platform extends GameObject{
	public Platform(double posX, double posY){
		super(posX, posY);
		this.setFitHeight(35);
		this.setFitWidth(35);
		this.getStyleClass().add("platform");
	}
}
