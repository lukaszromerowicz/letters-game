package Levels;

import GameAssets.*;
import javafx.scene.Node;

public class LevelFactory implements FactoryIF{

	@Override
	public GameObject createGameObject(String type, double posX, double posY) {
		GameObject object;
		
		if (type == "landscape")
			object = new LandscapePlatform(posX,posY);
		else if (type == "landscape-center")
			object = new LandscapeCenterPlatform(posX,posY);
		else if (type == "water")
			object = new LandscapeWater(posX,posY);
		else if (type == "water-center")
			object = new LandscapeWaterCenter(posX,posY);
		else if (type == "cloud")
			object = new Cloud(posX,posY);
		else
			object = new Platform(posX, posY);
		return object;
	}

}
