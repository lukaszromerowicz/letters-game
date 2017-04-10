package GameAssets;

/*
 * Grass platform class
 */
public class GrassPlatform extends Platform{
	public GrassPlatform(double posX, double posY)
	{
		super(posX, posY);
		this.getStyleClass().add("grass-platform");
	}
	
	/*
	 * Constructor for grass platform with directed tiles
	 * 
	 * @param direction Specifies type and direction of tile.
	 */
	public GrassPlatform(double posX, double posY, String direction)
	{
		this(posX,posY);
		if ( direction == "center") 
			this.getStyleClass().add("grass-platform-center");
		else if (direction == "half-mid")
			this.getStyleClass().add("grass-half-mid");
		else if (direction == "half-left")
			this.getStyleClass().add("grass-half-left");
		else if (direction == "half-right")
			this.getStyleClass().add("grass-half-right");
		else
			return;
		
	}
}

