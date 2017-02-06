package Screens;

import Main.AssignmentTemplate;
import GameAssets.Player;
import Levels.*;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScreen implements ScreenInterface{
	private MainScreen parent;
	private Pane content;
	private Level level;
	private Player player;
	private EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() 
	{
		
		private int pauseCount = 0;
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ESCAPE) {
				System.out.println("esc pressed");
				parent.changeScreen(AssignmentTemplate.MENU_SCREEN);
			}
			if(event.getCode() == KeyCode.P) {
				pauseCount++;
				
				if (pauseCount % 2 == 0)
					pauseGame(false);
				else
					pauseGame(true);
			}
			if(event.getCode() == KeyCode.DOWN) {
			}
			if(event.getCode() == KeyCode.LEFT){
				player.moveX(-10);
			}
			if(event.getCode() == KeyCode.RIGHT){
				player.moveX(10);
			}
		}
		
	};
	
	public GameScreen(){
		level = Levels.LEVEL1;
		draw();
	}
	

	@Override
	public void draw() {
		content = new Pane();
		content.setMinSize(800,600);
		content.setFocusTraversable(true);
		
		player = new Player();
		Pane levelRoot = level.draw();
		
		player.getObject().translateXProperty().addListener((obs,old,newValue) -> {
			int offset = newValue.intValue();
			if (offset > 600 && offset<level.getWidth()){
				levelRoot.setLayoutX(-(offset - 600));
			}
		});
		
		content.getChildren().add(levelRoot);
		content.getChildren().add(player.getObject());
		content.setOnKeyPressed(keyboardHandler);
		
		content.getStyleClass().add("game-screen");
	}
	
	public void movePlayerX(){
		
	}

	@Override
	public void setParent(MainScreen screenParent) {
		parent = screenParent;
	}


	@Override
	public void show() {
		if (!parent.getChildren().isEmpty())
			parent.getChildren().remove(0);
		parent.getChildren().add(content);
	}
	
	public void pauseGame(boolean isTrue){
		Group pauseObjects = new Group();
		Label lb1 = new Label();
		lb1.setTranslateX(300);
		lb1.setTranslateY(200);
		lb1.setText("Pause");
		
		Rectangle rg1 = new Rectangle(300,200);
		rg1.setTranslateX(250);
		rg1.setTranslateY(200);
		
		pauseObjects.getChildren().add(rg1);
		pauseObjects.getChildren().add(lb1);
		if (isTrue)
		{
			content.getChildren().add(pauseObjects);
			show();
		}
		else
		{
			content.getChildren().removeIf(n -> n instanceof Group);
			show();
		}
	}

}
