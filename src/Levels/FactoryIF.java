package Levels;

import javafx.scene.Node;

public interface FactoryIF {
	Node createPlatform(String type, int startX, int endX, int level);
}
