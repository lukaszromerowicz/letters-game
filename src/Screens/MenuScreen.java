package Screens;

import java.util.ArrayList;

import Main.AssignmentTemplate;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class MenuScreen implements ScreenInterface{
	private MainScreen controller;
	private GraphicsContext gc;
	private EventHandler<KeyEvent> keyboardHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ENTER) {
				controller.changeScreen(AssignmentTemplate.GAME_SCREEN);
			}
			if(event.getCode() == KeyCode.S) {
				System.out.println("A pressed.");
				controller.changeScreen(AssignmentTemplate.HOW_TO_SCREEN);
			}
		}
		
	};
	private EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent event) {
			controller.changeScreen(AssignmentTemplate.GAME_SCREEN);
		}
		
	};
	private ArrayList<Button> buttons;



	@Override
	public Node draw() {
		Group elements = new Group();
		Canvas canvas = new Canvas(800,600);
		canvas.setFocusTraversable(true);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.YELLOW);
		gc.fillRect(0,0,800,600);
		
		Button btn1 = new Button("New Game");
		btn1.setMinWidth(100);
		btn1.setLayoutY(100);
		btn1.setLayoutX(400- (btn1.getMinWidth()/2));
		btn1.setOnMouseClicked(mouseEvent);
		
		elements.getChildren().add(canvas);
		elements.getChildren().add(btn1);
		elements.setOnKeyPressed(keyboardHandler);
		btn1.setOnMouseClicked(mouseEvent);
		
		return elements;
	}

	@Override
	public void setParent(MainScreen screenParent) {
		// TODO Auto-generated method stub
		controller = screenParent;
	}
	
	private void startGame(){
		//controller.changeScreen(screen);
	}

}
