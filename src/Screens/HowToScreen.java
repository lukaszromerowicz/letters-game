package Screens;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class HowToScreen implements ScreenInterface{
	MainScreen controller;
	GraphicsContext gc;
	
	@Override
	public Node draw() {
		Group elements = new Group();
		Canvas canvas = new Canvas(800,600);
		canvas.setFocusTraversable(true);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0,0,800,600);
		
		Button btn1 = new Button();
		
		elements.getChildren().add(canvas);
		
		return elements;
		
	}

	@Override
	public void setParent(MainScreen screenParent) {
		// TODO Auto-generated method stub
		controller = screenParent;
	}

}
