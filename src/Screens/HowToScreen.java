package Screens;

import Main.AssignmentTemplate;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/*
 * How to play screen class
 */
public class HowToScreen implements ScreenInterface{
	MainScreen parent;
	Pane content;
	private EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() 
	{
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ESCAPE) {
				parent.changeScreen(AssignmentTemplate.MENU_SCREEN);
			}
		}
	};
	
	/*
	 * Constructor, draws the content
	 */
	public HowToScreen()
	{
		draw();
	}
	
	/*
	 * @see Screens.ScreenInterface#draw()
	 */
	@Override
	public void draw() {
		// Initialise content pane
		content = new Pane();
		content.setFocusTraversable(true);
		content.setMinSize(800,600);
		
		// Game instructions
		Label description = new Label();
		description.setText("Collect all the letters to complete a word. \nThe more letters you collect the higher is the score!");
		description.setTranslateX(200);
		description.setTranslateY(170);
		description.getStyleClass().add("label-small");
		
		// Jump instructions
		ImageView spaceImage = new ImageView();
		spaceImage.setTranslateX(150);
		spaceImage.setTranslateY(250);
		spaceImage.getStyleClass().add("space-key");
		
		Label spaceLabel = new Label();
		spaceLabel.setText("jump");
		spaceLabel.setTranslateX(270);
		spaceLabel.setTranslateY(320);
		spaceLabel.getStyleClass().add("label");
		
		// Move instructions
		ImageView leftArrowImage = new ImageView();
		leftArrowImage.setTranslateX(500);
		leftArrowImage.setTranslateY(250);
		leftArrowImage.getStyleClass().add("left-arrow-key");
		

		ImageView rightArrowImage = new ImageView();
		rightArrowImage.setTranslateX(560);
		rightArrowImage.setTranslateY(250);
		rightArrowImage.getStyleClass().add("right-arrow-key");
		

		Label moveLabel = new Label();
		moveLabel.setText("move");
		moveLabel.setTranslateX(527);
		moveLabel.setTranslateY(320);
		moveLabel.getStyleClass().add("label");
		
	
		content.getChildren().add(description);
		content.getChildren().add(spaceLabel);
		content.getChildren().add(moveLabel);
		content.getChildren().add(spaceImage);
		content.getChildren().add(leftArrowImage);
		content.getChildren().add(rightArrowImage);
		content.setOnKeyPressed(keyboardHandler);
		content.getStyleClass().add("how-to");
	}

	/*
	 * @see Screens.ScreenInterface#setParent(Screens.MainScreen)
	 */
	@Override
	public void setParent(MainScreen screenParent) {
		parent = screenParent;
	}

	/*
	 * @see Screens.ScreenInterface#show()
	 */
	@Override
	public void show() {
		if (!parent.getChildren().isEmpty())
			parent.getChildren().remove(0);
		parent.getChildren().add(content);
	}

	@Override
	/*
	 * @see Screens.ScreenInterface#getContent()
	 */
	public Pane getContent() {
		return content;
	}

}
