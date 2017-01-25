package Screens;

import Main.AssignmentTemplate;
import javafx.scene.Group;
import javafx.scene.Node;
public class MainScreen extends Group implements ScreenInterface{
	ScreenInterface activeScreen,gameScreen = null;
	
	public MainScreen(ScreenInterface mainScreen){
		activeScreen = AssignmentTemplate.MENU_SCREEN;
		activeScreen.setParent(this);
		draw();
	}
	
	
	@Override
	public Node draw() {
		if (!getChildren().isEmpty())
			getChildren().remove(0);
		getChildren().add(activeScreen.draw());
		return activeScreen.draw();
	}

	@Override
	public void setParent(MainScreen screenParent) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeScreen(ScreenInterface screen){
		activeScreen = screen;
		activeScreen.setParent(this);
		draw();
	}

}
