package Levels;

import GameAssets.GameObject;

/*
 * Level factory class
 * 
 */
public interface FactoryIF {
	// Creates game object
	GameObject createGameObject(String type, double posX, double posY);
	// Creates game score point
	GameObject createGamePoint(String value, double posX, double posY);
}
