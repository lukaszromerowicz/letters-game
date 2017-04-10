package Levels;

import GameAssets.*;

/*
 * Implementation of factory. Used to create game objects
 */
public class LevelFactory implements FactoryIF{

	/*
	 * @see Levels.FactoryIF#createGameObject(java.lang.String, double, double)
	 */
	@Override
	public GameObject createGameObject(String type, double posX, double posY) {
		GameObject object;
		
		if (type.equals("grass"))
			object = new GrassPlatform(posX,posY);
		else if (type.equals("grass-center"))
			object = new GrassPlatform(posX,posY,"center");
		else if (type.equals("grass-half-mid"))
			object = new GrassPlatform(posX,posY,"half-mid");
		else if (type.equals("grass-half-left"))
			object = new GrassPlatform(posX,posY,"half-left");
		else if (type.equals("grass-half-right"))
			object = new GrassPlatform(posX,posY,"half-right");
		else if (type.equals("brick"))
			object = new BrickPlatform(posX,posY);
		else if (type.equals("water"))
			object = new WaterUnit(posX,posY);
		else if (type.equals("water-center"))
			object = new WaterUnit(posX,posY,"center");
		else if (type.equals("cloud"))
			object = new Cloud(posX,posY);
		else if (type.equals("platform-rope"))
			object = new RopePlatform(posX,posY);
		else
			object = new GrassPlatform(posX,posY);
		
		return object;
	}

	/*
	 * @see Levels.FactoryIF#createGamePoint(java.lang.String, double, double)
	 */
	@Override
	public GameObject createGamePoint(String value, double posX, double posY) {
		GameObject point;
		point = new ScorePoint(value,posX,posY);
				
		return point;
	}

}
