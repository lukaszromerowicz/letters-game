package Screens;

import Main.AssignmentTemplate;
import javafx.scene.layout.Pane;

public class MainScreen extends Pane implements ScreenInterface{
    // Active screen field
    private ScreenInterface activeScreen;
	
	public MainScreen(ScreenInterface mainScreen){
		// Set default active screen to menu
		activeScreen = AssignmentTemplate.MENU_SCREEN;
		activeScreen.setParent(this);
		// Draw and show the screen
		draw();
		show();
	}
	
	
	@Override
	// Draws the active screen
	public void draw() {
		activeScreen.draw();
	}
	
	
	@Override
	// Shows the active screen
	public void show() {
		activeScreen.show();
	}

	@Override
	public void setParent(MainScreen screenParent) {
		// Main screen has no parent. Do nothing
	}
	
	// Changes screens
	public void changeScreen(ScreenInterface screen){
		activeScreen = screen;
		activeScreen.setParent(this);
		show();
	}



}
