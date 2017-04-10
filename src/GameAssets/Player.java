package GameAssets;


import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

/*
 * Player object class
 */
public class Player extends GameObject{
    private Animation animationMoveRight;
    private Animation animationMoveLeft;
    private Animation animationMoveUp;
	private Point2D velocity;
	private int lives;
	private boolean canJump;
	
	/*
	 * Constructor creates player and initialises animations
	 * Player is created at the default level start position
	 */
	public Player() {
		super(0,460);
		velocity = new Point2D(0,0);
		canJump = true;
		lives = 3;
		this.getStyleClass().add("player");
		this.setFitWidth(33);
		this.setFitHeight(46);
		initAnimations();
	}
	
	/*
	 * Returns player velocity
	 * 
	 * @return velocity
	 */
	public Point2D getVelocity()
	{
		return velocity;
	}
	
	/*
	 * Return number of player lives
	 * 
	 * @return lives
	 */
	public int getLives()
	{
		return lives;
	}
	
	/* 
	 * Sets player velocity
	 * 
	 * @param point Specifies velocity.
	 */
	public void setVelocity(Point2D point)
	{
		velocity = point;
	}
	
	/* 
	 * Sets if player can jump
	 * 
	 * @param jump Specifies if player can jump.
	 */
	public void setCanJump(boolean jump)
	{
		this.canJump = jump;
	}
	
	/*
	 * Decreases player lives
	 */
	public void decreaseLive()
	{

		AudioClip decreaseLiveSound = new AudioClip(this.getClass().getResource("Sounds/hit.wav").toExternalForm());
		decreaseLiveSound.play();
		lives--;
	}
	
	
	/*
	 * Performs player jump
	 */
	public void jump()
	{
		if (canJump)
		{
			AudioClip jumpSound = new AudioClip(this.getClass().getResource("Sounds/jump.wav").toExternalForm());
			jumpSound.play();
			velocity = velocity.add(0,-25);
			canJump = false;
		}
	}
	
	/*
	 * Moves player in X axis.
	 * 
	 * @param movingRight Specifies if player is moving right.
	 */
	public void moveX(boolean movingRight)
	{
			this.setTranslateX(this.getTranslateX() + (movingRight ? 1 : -1 ));
	}
	
	/*
	 * Moves player in Y axis.
	 * 
	 * @param movingUp Specifies if player is moving upwards.
	 */
	public void moveY(boolean movingUp)
	{
			this.setTranslateY(this.getTranslateY() + (movingUp ? -1 : 1));	
			
			if (movingUp)
			{
				animate("up");
			}
			else
			{
				animate("down");
			}
	}
	
	/*
	 * Animates player movement
	 * 
	 * @param direction Specifies direction of player movement.
	 */
	public void animate(String direction)
	{
		
		if (direction == "right")
			animationMoveRight.play();
		else if (direction == "left")
			animationMoveLeft.play();
		else if (direction == "up")
			animationMoveUp.play();
		else
			return;
	}
	
	/*
	 * Initialises animations
	 */
	private void initAnimations()
	{
		animationMoveRight = new SpriteAnimation(
                this,
                Duration.millis(500),
                5, 5,
                0, 0,
                66, 92
        );
        animationMoveRight.setCycleCount(1);
        
        animationMoveLeft = new SpriteAnimation(
                this,
                Duration.millis(500),
                5, 5,
                0, 92,
                66, 92
        );
        animationMoveLeft.setCycleCount(1);
        
        animationMoveUp = new SpriteAnimation(
                this,
                Duration.millis(500),
                2, 2,
                0, 184,
                66, 92
        );
        animationMoveUp.setCycleCount(1);    

        animationMoveRight.play();
	}
	
	
	
}
