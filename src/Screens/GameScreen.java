package Screens;

import Main.AssignmentTemplate;

import java.util.HashMap;

import com.sun.javafx.geom.Point2D;

import GameAssets.GameObject;
import GameAssets.Player;
import Levels.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
	// Content variables
	private Pane content;
	private Level level;
	private Pane levelRoot;
	private int levelOffset;
	// Player
	private Player player;
	// Keycodes
	private HashMap<KeyCode,Boolean> keys = new HashMap<KeyCode,Boolean>();
	/*
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
				if((player.getTranslateX()) >0 ) {
					if (player.getTranslateX() <= 433 && levelRoot.getLayoutX()<0) {
						levelOffset+= 5;
						System.out.println(levelOffset);
						levelRoot.setLayoutX(levelOffset);
						player.animate(false);
					}
					else
					{
						player.moveX(-5,false);
					}
				}
			}
			if(event.getCode() == KeyCode.RIGHT){
				if((player.getTranslateX() + 33) < 800 ) {
					if (player.getTranslateX() >= 433 && levelRoot.getLayoutX()>866-level.getWidth()) {
						levelOffset-= 5;
						System.out.println(levelOffset);
						levelRoot.setLayoutX(levelOffset);
						player.animate(true);
					}
					else
					{
						player.moveX(5,true);
					}
				}
			}
		}
		
	};
	*/
	
	// Constructor
	public GameScreen(){
		level = Levels.LEVEL1;
		levelRoot = level.draw();
		draw();
	}
	

	@Override
	public void draw() {
		content = new Pane();
		content.setMinSize(800,600);
		content.setFocusTraversable(true);
		
		player = new Player();
		
		/*
		player.translateXProperty().addListener((obs,old,newValue) -> {
			int offset = newValue.intValue();
			System.out.println(offset);
			if (offset > 433 && offset<level.getWidth()-433){
				levelRoot.getLayoutX();
				levelRoot.setLayoutX(-(offset-433));
			}
		});
		*/
		
		content.getChildren().add(levelRoot);
		content.getChildren().add(player);
		content.setOnKeyPressed(event -> keys.put(event.getCode(),true));
		content.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		
		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				update();
			}
			
		};
		
		timer.start();
		
		content.getStyleClass().add("game-screen");
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
	
	public void update()
	{
		if (isKeyPressed(KeyCode.SPACE))
		{
			jumpPlayer();
		}
		
		if (isKeyPressed(KeyCode.RIGHT))
		{
			movePlayerX(5,true);
		}
		
		if (isKeyPressed(KeyCode.LEFT))
		{
			movePlayerX(-5,false);
		}
		
		
		if (player.getVelocity().getY() < 10) {
			player.setVelocity(player.getVelocity().add(0,1));
		}
	
		//System.out.println("second: " +player.getVelocity().getY());
		movePlayerY(((int) player.getVelocity().getY()));
		
	}

	private boolean isKeyPressed(KeyCode key)
	{
		return keys.getOrDefault(key,false);
	}
	
	private void movePlayerX(int value,boolean right)
	{

			for (int i=0; i< Math.abs(value); i++)
			{
				for (GameObject platform : level.getPlatforms())
				{
					if (right) {
						if(player.getBoundsInLocal().intersects(platform.getBoundsInParent()))
							if (player.getX() + 33 == platform.getTranslateX())
								return;
						
					} else {
						if(player.getBoundsInLocal().intersects(platform.getBoundsInParent()))
							if (player.getX() == platform.getTranslateX() + 33)
								return;
					}
				}
				
				if (right) {
				if((player.getX() + 33) < 800 ) {
					if (player.getX() >= 433 && levelRoot.getLayoutX()>866-level.getWidth()) {
						levelOffset-= 1;
						//System.out.println(levelOffset);
						levelRoot.setLayoutX(levelOffset);
					} else {
						player.moveX(true);
				}
				player.animate("right");
				}
				} else {

				if((player.getX()) >0 ) {
					if (player.getX() <= 433 && levelRoot.getLayoutX()<0) {
						levelOffset+= 1;
						//System.out.println(levelOffset);
						levelRoot.setLayoutX(levelOffset);
					} else {
						player.moveX(false);
					}
					player.animate("left");
				}
				}
			}
	}
	
	private void movePlayerY(int value)
	{
		
		boolean movingUp = value < 0;
		
		for (int i=0; i<= Math.abs(value); i++)
		{

			for (GameObject platform : level.getPlatforms())
			{
				if (movingUp) {
					if(player.getBoundsInParent().intersects(platform.getBoundsInParent()))
					{
						if (player.getY() + 45 == platform.getY())
						{
							player.setY(player.getY() - 1);
							return;
						}
					}
					
				} else {
					
					if( player.getY() < platform.getY())
						System.out.println("Player Y: " + player.getY() + "Platform Y: " + platform.getY());

					
					if(player.getBoundsInLocal().intersects(platform.getBoundsInLocal()))
					{
						System.out.println("Platform X:" + platform.getX());
						System.out.println("Player X:" + player.getX());
						if (player.getY() == platform.getY() -44 )
						{
							player.setJump(true);
							return;
						}

					}
				}
				
			}
			
			//System.out.println("value: " + value);
			player.moveY(movingUp);
		}

		//player.moveY(true);
	}
	
	private void jumpPlayer()
	{
		if(player.getJump())
		{
			System.out.println("elo elo");
			player.setVelocity(player.getVelocity().add(0,-30));
			System.out.println(player.getVelocity().getY());
			player.setJump(false);
		}
	}
}
