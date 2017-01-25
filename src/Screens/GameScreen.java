package Screens;

import Main.AssignmentTemplate;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameScreen implements ScreenInterface{
	MainScreen controller;
	private GraphicsContext gc;
	private EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ESCAPE) {
				System.out.println("Escape pressed.");
				controller.changeScreen(AssignmentTemplate.MENU_SCREEN);
			}
		}
		
	};

	@Override
	public Node draw() {

		Canvas canvas = new Canvas(800,600);
		canvas.setFocusTraversable(true);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.fillRect(0,0,800,600);
		

		canvas.setOnKeyPressed(keyboardHandler);
		
		return canvas;
		
	}

	@Override
	public void setParent(MainScreen screenParent) {
		// TODO Auto-generated method stub
		controller = screenParent;
	}

}
