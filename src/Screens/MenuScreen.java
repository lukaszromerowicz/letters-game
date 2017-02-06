package Screens;

import java.util.ArrayList;

import Main.AssignmentTemplate;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class MenuScreen implements ScreenInterface{
	private MainScreen parent;
	private Pane content;
	private ArrayList<Button> menuOptions;
	private int currentOptionIndex;
	private EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.ENTER) {
				switch (currentOptionIndex) {
						case 0 : parent.changeScreen(new GameScreen());
						break;
						
						case 1 : parent.changeScreen(AssignmentTemplate.HOW_TO_SCREEN);
						break;
						
						case 2 : System.exit(0);
						break;
				}
			}
			if (event.getCode() == KeyCode.DOWN) {
				if (currentOptionIndex + 1 <= menuOptions.size()-1)
					currentOptionIndex++;
				else
					currentOptionIndex = 0;
				
				selectMenuOption();
			}
			if (event.getCode() == KeyCode.UP) {
				if (currentOptionIndex-1 >= 0)
					currentOptionIndex--;
				else
					currentOptionIndex = menuOptions.size()-1;
				
				selectMenuOption();
			}
		}
		
	};
	
	public MenuScreen(){
		currentOptionIndex = 0;
		menuOptions = new ArrayList<Button>();
	}

	
	@Override
	// Draws menu screen
	public void draw() {
		
		// Initialise content pane
		content = new Pane();
		content.setMinSize(800,600);
		
		// Add buttons
		Button startGame = new Button("New game");
		startGame.setMinWidth(200);
		startGame.setLayoutY(150);
		startGame.setLayoutX(290);
		startGame.getStyleClass().add("active");
		menuOptions.add(startGame);
		
		Button howToPlay = new Button("How to play");
		howToPlay.setMinWidth(200);
		howToPlay.setLayoutY(250);
		howToPlay.setLayoutX(290);
		menuOptions.add(howToPlay);
		
		Button exitGame = new Button("Exit");
		exitGame.setMinWidth(200);
		exitGame.setLayoutY(350);
		exitGame.setLayoutX(290);
		menuOptions.add(exitGame);
		
		content.getChildren().add(startGame);
		content.getChildren().add(howToPlay);
		content.getChildren().add(exitGame);
		content.setOnKeyPressed(keyboardHandler);
		
		// Add styling
		content.getStyleClass().add("menu");
		
	}

	@Override
	// Sets screen parent
	public void setParent(MainScreen screenParent) {
		parent = screenParent;
	}

	@Override
	// Shows screen
	public void show() {
		if (!parent.getChildren().isEmpty())
			parent.getChildren().remove(0);
		parent.getChildren().add(content);
		
	}
	
	private void selectMenuOption()
	{
		for (Button b : menuOptions){
			if (b.getStyleClass().contains("active"))
				b.getStyleClass().remove("active");
		}
		menuOptions.get(currentOptionIndex).getStyleClass().add("active");
	}
		
	

}
