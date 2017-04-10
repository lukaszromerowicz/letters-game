package Screens;

import Main.AssignmentTemplate;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 * Main screen class
 */
public class MainScreen extends Pane implements ScreenInterface{
    // Active screen field
    private ScreenInterface activeScreen;
	
	public MainScreen(ScreenInterface mainScreen){
		// Set default active screen to menu
		activeScreen = AssignmentTemplate.MENU_SCREEN;
		activeScreen.setParent(this);
		// Generate and show the screen
		draw();
		show();
	}
	
	/*
	 * @see Screens.ScreenInterface#draw()
	 */
	@Override
	public void draw() {
		activeScreen.draw();
	}
	
	/*
	 * @see Screens.ScreenInterface#show()
	 */
	@Override
	public void show() {
		activeScreen.show();
	}

	/*
	 * @see Screens.ScreenInterface#setParent(Screens.MainScreen)
	 */
	@Override
	public void setParent(MainScreen screenParent) {
		// Main screen has no parent. Do nothing
	}
	
	/*
	 * @see Screens.ScreenInterface#getContent()
	 */
	@Override
	public Pane getContent() {
		return activeScreen.getContent();
	}

	
	// Changes screens
	public void changeScreen(ScreenInterface screen){
			activeScreen = screen;
			activeScreen.setParent(this);
			show();
			FadeTransition ft2 = new FadeTransition(Duration.millis(600), getContent());
		     ft2.setFromValue(0);
		     ft2.setToValue(1);
		     ft2.play();
	}

}
