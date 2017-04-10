package GameAssets;

import javafx.scene.media.AudioClip;

/*
 * Score point class
 */
public class ScorePoint extends GameObject{
	private boolean collected;
	private String value;

	/*
	 * Constructor for score point
	 * 
	 * @param value Specifies the value of point i.e. "s"
	 */
	public ScorePoint(String value, double x, double y) {
		super(x, y);
		this.setFitHeight(35);
		this.setFitWidth(35);
		collected = false;
		this.value = value;
		this.getStyleClass().add("letter-" + this.value);
	}
	
	/*
	 * Performs point collection
	 */
	public void collect()
	{
		collected = true;
		AudioClip collectSound = new AudioClip(this.getClass().getResource("Sounds/coin1.wav").toExternalForm());
		collectSound.play();
		this.setVisible(false);
	}
	
	/*
	 * Returns value of the point
	 * 
	 * @return value
	 */
	public String getValue()
	{
		return value;
	}
	
	/*
	 * Returns true if point was collected
	 * 
	 * @return collected
	 */
	public boolean isCollected()
	{
		return collected;
	}

}
