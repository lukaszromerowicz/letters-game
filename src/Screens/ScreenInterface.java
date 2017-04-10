package Screens;

import javafx.scene.layout.Pane;

/*
 * Screen interface class.
 */
public interface ScreenInterface {
	 // Generates and draws content of the screen
	void draw();
	// Shows the content of the screen
	void show(); 
	// Sets parent of the screen
	void setParent(MainScreen screenParent); 
	// Returns content of the screen
	Pane getContent(); 
}
