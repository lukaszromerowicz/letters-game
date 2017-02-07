package Levels;

import GameAssets.GameObject;

public interface FactoryIF {
	GameObject createGameObject(String type, double posX, double posY);
}
