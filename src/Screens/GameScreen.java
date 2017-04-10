package Screens;

import Main.AssignmentTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import GameAssets.LiveIcon;
import GameAssets.Player;
import GameAssets.ScorePoint;
import Levels.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/*
 * Game screen class
 */
public class GameScreen implements ScreenInterface{
	private MainScreen parent;
	// Content variables
	private Pane content, hud;
	private int levelNumber;
	private Level level;
	private Pane levelRoot;
	private int levelOffset;
	private AnimationTimer timer;
	// Player
	private Player player;
	// Key codes and keyboard events
	private HashMap<KeyCode,Boolean> keys = new HashMap<KeyCode,Boolean>();
	private EventHandler<KeyEvent> levelFinishHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ENTER)
			{
				//content.getChildren().remove(content.lookup(".message"));
				content.setOnKeyPressed(localEvent -> keys.put(localEvent.getCode(),true));
				finishLevel();
			}
		}
		
	};
	private EventHandler<KeyEvent> gameFinishHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ENTER)
			{
				parent.changeScreen(new GameScreen());
			}
			else if (event.getCode() == KeyCode.ESCAPE)
			{
				parent.changeScreen(AssignmentTemplate.MENU_SCREEN);
			}
		}
		
	};
	private EventHandler<KeyEvent> gameLeavingHandler = new EventHandler<KeyEvent>() 
	{

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.ENTER)
			{
				parent.changeScreen(AssignmentTemplate.MENU_SCREEN);
			}
			else if (event.getCode() == KeyCode.ESCAPE)
			{
				content.getChildren().removeAll(content.lookupAll(".message"));
				content.setOnKeyPressed(localEvent -> keys.put(localEvent.getCode(),true));
			}
		}
		
	};
	
	
	// Gameplay variables
	private ArrayList<ScorePoint> scoredPoints;
	
	// Constructor for GameScreen, default level is set to 1 
	public GameScreen(){
		scoredPoints = new ArrayList<ScorePoint>();
		
		levelNumber = 1;
		level = Levels.getLevel(levelNumber);
		levelRoot = level.draw();
		
		draw();
	}
	
	
	/*
	 * @see Screens.ScreenInterface#draw()
	 */
	@Override
	public void draw() {
		content = new Pane();
		content.setMinSize(800,600);
		content.setFocusTraversable(true);
		
		player = new Player();

		initHud();

		// Listener on player Y property, checking if player falls into gap
		player.translateYProperty().addListener((obs,old,newValue) ->
		{
			if (newValue.intValue()>450)
				player.setCanJump(false);
			if (newValue.intValue()>550)
			{
				player.decreaseLive();
				hud.getChildren().remove(hud.lookup(".hud-live"));
				timer.stop();
				if (player.getLives() > 0) {
					levelOffset = 0;
						for (Object object: levelRoot.getChildren().toArray())
						{
							((Node) object).setLayoutX(levelOffset);
						}
			
					level.setOffset(levelOffset);
					player.setTranslateX(0);
					player.setTranslateY(460);
					timer.start();
				} else {
					showGameOverMessage();
				}
			}		
		});
		
		// Listener on player X property, checking if player finished the level
		player.translateXProperty().addListener((obs,old,newValue) ->
		{
			if (newValue.intValue()>700)
			{
				timer.stop();
				showLevelFinishMessage();
			}
				
		});
		
		// Adding elements to content
		content.getChildren().add(levelRoot);
		content.getChildren().add(player);
		content.getChildren().add(hud);
		content.setOnKeyPressed(event -> keys.put(event.getCode(),true));
		content.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		
		// Game frame animation
		timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				update();
			}
			
		};
		
		timer.start();
		
		content.getStyleClass().add("game-screen");
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
	
	/*
	 * @see Screens.ScreenInterface#getContent()
	 */
	@Override
	public Pane getContent() {
			return content;
	}
	
	
	/*
	 * Changes game level
	 * 
	 * @param levelNumber Specifies level number.
	 */
	private void changeLevel(int levelNumber)
	{
		levelOffset=0;
		level = Levels.getLevel(levelNumber);
		levelRoot = level.draw();
		
		player.setTranslateX(0);
		player.setTranslateY(460);
		
		content.getChildren().clear();
		hud.getChildren().removeIf(n -> n instanceof ScorePoint);
		scoredPoints.clear();
		content.getChildren().add(levelRoot);
		content.getChildren().add(player);
		content.getChildren().add(hud);
	}

	/*
	 * Game frame method
	 * 
	 */
	public void update()
	{
		if (isKeyPressed(KeyCode.SPACE))
		{
			player.jump();
		}
		
		if (isKeyPressed(KeyCode.RIGHT))
		{
			movePlayerX(5);
		}
		
		if (isKeyPressed(KeyCode.LEFT))
		{
			movePlayerX(-5);
		}
		
		if (isKeyPressed(KeyCode.ESCAPE))
		{
			showLeavingMessage();
		}
		
		
		if (player.getVelocity().getY() < 10) {
			player.setVelocity(player.getVelocity().add(0,1));
		}
	
		
		movePlayerY(((int) player.getVelocity().getY()));
		
	}

	/*
	 * Checks if key is pressed.
	 * 
	 * @param key Specifies keyCode of pressed key.
	 */
	private boolean isKeyPressed(KeyCode key)
	{
		return keys.getOrDefault(key,false);
	}
	
	/*
	 * Moves player in X axis.
	 * 
	 * @param value Specifies value of movement.
	 */
	private void movePlayerX(int value)
	{
		// If player is moving right value is higher than 0
		boolean right = value >0;
		
		for (int i=0; i< Math.abs(value); i++)
		{	
			if (right) 
			{
				if((player.getTranslateX() + 33) < 800 ) 
				{
					if (player.getTranslateX() >= 433 && level.getOffset()>866-level.getWidth()) 
					{
						levelOffset-= 1;
				
						for (Object object: levelRoot.getChildren().toArray())
						{
							((Node) object).setLayoutX(levelOffset);
						}
						
						level.setOffset(levelOffset);
						
						
					} else {
					
						player.moveX(true);
					}
						
					player.animate("right");
				}
			} else {
				if((player.getTranslateX()) >0 ) 
				{
						player.moveX(false);
						player.animate("left");
				}
			}
		}
	}
	
	/*
	 * Moves player in Y axis.
	 * 
	 * @param value Specifies value of movement.
	 */
	private void movePlayerY(int value)
	{
		
		// If player is moving up value is less than 0
		boolean movingUp = value < 0;
		
		for (int i=0; i<= Math.abs(value); i++)
		{
			
			if (!level.getPoints().isEmpty())
			{
				for (ScorePoint point : level.getPoints())
				{
					if(player.getBoundsInParent().intersects(point.getBoundsInParent()))
					{
						updateScore(point);
					}
				
				}
				level.updateScore();
			}

			for (Node platform : level.getPlatforms())
			{
				if (movingUp) {
					if(player.getBoundsInParent().intersects(platform.getBoundsInParent()))
					{

						if (player.getTranslateY() - 45 == platform.getTranslateY())
						{
							player.setTranslateY(player.getTranslateY() + 1);
							return;
						}
					}
					
				} else {
					if(player.getBoundsInParent().intersects(platform.getBoundsInParent()))
					{
						if (player.getTranslateY() == platform.getTranslateY()-44)
						{
							player.setCanJump(true);
							return;
						}

					}
				}
			}
			
			player.moveY(movingUp);
		}
	}
	
	/*
	 * Updates score shown in HUD
	 * 
	 * @param point Specifies scored point.
	 */
	private void updateScore(ScorePoint point)
	{	
		if (!scoredPoints.isEmpty()) 
		{
			ScorePoint newPoint = new ScorePoint(point.getValue(),scoredPoints.get(scoredPoints.size()-1).getTranslateX() + 50, 20);
			scoredPoints.add(newPoint);
			hud.getChildren().add(newPoint);
		} else {
			ScorePoint newPoint = new ScorePoint(point.getValue(), 50, 20);
			hud.getChildren().add(newPoint);
			scoredPoints.add(newPoint);
			
		}
		
		point.collect();
	}
	
	/*
	 * Initialises HUD
	 * 
	 */
	private void initHud()
	{
		hud = new Pane();
		hud.setMinSize(800,100);
		hud.setTranslateX(0);
		hud.setTranslateX(0);
		
		for (int i=0; i<player.getLives(); i++)
		{
			
			LiveIcon icon = new LiveIcon(680 + i *30 ,20);
			hud.getChildren().add(icon);
			
		}
		
	}
	
	/*
	 * Called when player finishes level
	 */
	private void finishLevel()
	{
	
		if (Levels.getLevel(levelNumber) != null) 
		{
			changeLevel(levelNumber);
			timer.start();
		}
		else
		{
			parent.changeScreen(AssignmentTemplate.MENU_SCREEN);
		}
	}
	
	/*
	 * Called when player wants to leave game
	 */
	private void showLeavingMessage()
	{
		Pane message = new Pane();
		message.setMinSize(400, 150);
		message.setTranslateX(200);
		message.setTranslateY(100);
		message.getStyleClass().add("message");
		

		Label gameOver = new Label();
		gameOver.setText("Exit game?");
		gameOver.setTranslateX(140);
		gameOver.setTranslateY(50);
		gameOver.getStyleClass().add("label-dark");
		
		Label continueLabel = new Label();
		continueLabel.setText("Press ESC to continue game or ENTER exit.");
		continueLabel.setTranslateX(35);
		continueLabel.setTranslateY(90);
		continueLabel.getStyleClass().add("label-dark-small");

		
		content.setOnKeyPressed(gameLeavingHandler);
		message.getChildren().add(continueLabel);
		message.getChildren().add(gameOver);
		content.getChildren().add(message);
		
	}
	
	/*
	 * Called when player has no more lives
	 */
	private void showGameOverMessage()
	{
		AudioClip gameOverSound = new AudioClip(player.getClass().getResource("Sounds/game_over.wav").toExternalForm());
		gameOverSound.play();

		Pane message = new Pane();
		message.setMinSize(400, 150);
		message.setTranslateX(175);
		message.setTranslateY(100);
		message.getStyleClass().add("message");
		

		Label gameOver = new Label();
		gameOver.setText("Game over!");
		gameOver.setTranslateX(150);
		gameOver.setTranslateY(50);
		gameOver.getStyleClass().add("label-dark");
		
		Label continueLabel = new Label();
		continueLabel.setText("Press ENTER to play again or ESC to move back to menu.");
		continueLabel.setTranslateX(25);
		continueLabel.setTranslateY(90);
		continueLabel.getStyleClass().add("label-dark-small");

		
		content.setOnKeyPressed(gameFinishHandler);
		message.getChildren().add(continueLabel);
		message.getChildren().add(gameOver);
		content.getChildren().add(message);
	}
	
	/*
	 * Called when user finishes the level. Updates levelNumber.
	 */
	private void showLevelFinishMessage()
	{
		
		AudioClip levelFinishSound = new AudioClip(player.getClass().getResource("Sounds/achievement.wav").toExternalForm());
		levelFinishSound.play();
		
		Pane message = new Pane();
		message.setMinSize(400, 350);
		message.setTranslateX(200);
		message.setTranslateY(100);
		message.getStyleClass().add("message");
		
		Label congratulations = new Label();
		congratulations.setText("Congratulations!");
		congratulations.setTranslateX(100);
		congratulations.setTranslateY(50);
		congratulations.getStyleClass().add("label-dark");
		
		Label score = new Label();
		score.setText("Your score: " + scoredPoints.size()*100);
		score.setTranslateX(100);
		score.setTranslateY(100);
		score.getStyleClass().add("label-dark");
		
		Label continueLabel = new Label();
		continueLabel.setText("Press enter to continue.");
		continueLabel.setTranslateX(110);
		continueLabel.setTranslateY(270);
		continueLabel.getStyleClass().add("label-dark-small");
		
		
		message.getChildren().add(score);
		message.getChildren().add(congratulations);
		message.getChildren().add(continueLabel);
		
		for (int i=0; i<=level.getWord().length()-1; i++)
		{
			
			boolean found = false;
			
			for (ScorePoint point : scoredPoints)
			{

				if(point.getValue().equals(Character.toString(level.getWord().charAt(i))))
				{
					found = true;
				}
			}
			
			if (found) {
				ScorePoint point = new ScorePoint(Character.toString(level.getWord().charAt(i)),200-(level.getWord().length()*20) + i *40, 160);
				message.getChildren().add(point);
			}
			else {
				Label pointLabel = new Label();
				pointLabel.setText(Character.toString(level.getWord().charAt(i)).toUpperCase());
				pointLabel.setTranslateY(160);
				pointLabel.setTranslateX(200-(level.getWord().length()*20) + i*40);
				pointLabel.getStyleClass().add("point-fail");
				message.getChildren().add(pointLabel);
			}
		}
		
		Label scoreLabel = new Label();
		scoreLabel.setText("You have collected " + scoredPoints.size() + " letters out of " + level.getWord().length() + ".");
		scoreLabel.setTranslateX(60);
		scoreLabel.setTranslateY(220);
		scoreLabel.getStyleClass().add("label-dark-small");

		message.getChildren().add(scoreLabel);
		
		levelNumber++;
		
		if (Levels.getLevel(levelNumber) == null) {
			Label finishMessage = new Label();
			finishMessage.setText("That was the last level. Thank you for playing.");
			finishMessage.setTranslateX(30);
			finishMessage.setTranslateY(240);
			finishMessage.getStyleClass().add("label-dark-small");
			message.getChildren().add(finishMessage);
			
		}
		
		content.setOnKeyPressed(levelFinishHandler);
		content.getChildren().add(message);
			
	}

}

