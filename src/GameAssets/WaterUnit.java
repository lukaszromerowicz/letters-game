package GameAssets;

/*
 * Water unit class
 */
public class WaterUnit extends Platform{

	public WaterUnit(double posX, double posY) 
	{
		super(posX, posY);
		this.getStyleClass().add("water");
	}

	/*
	 * Constructor for water unit platform with directed tiles
	 * 
	 * @param direction Specifies type and direction of tile.
	 */
	public WaterUnit(double posX, double posY, String direction)
	{
		this(posX,posY);
		switch (direction)
		{
			case "center" : 
				this.getStyleClass().add("water-center");
				break;
			case "left" :
				this.getStyleClass().add("water-left");
				break;
			case "right" :
				this.getStyleClass().add("water-right");
				break;
			default: 
				break;
		}
	}

}
