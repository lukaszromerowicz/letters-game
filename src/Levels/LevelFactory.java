package Levels;

import GameAssets.*;
import javafx.scene.Node;

public class LevelFactory implements FactoryIF{

	@Override
	public Node createPlatform(String type,int startX, int endX, int level) {
		Platform platform;
		if ( type == "landscape") 
			platform = new LandscapePlatform(startX,endX,level);
		else if (type == "wooden")
			platform = new WoodenPlatform(startX,endX,level);
		else
			platform = new Platform(startX,endX,level);
		return platform.getObject();
	}

}
