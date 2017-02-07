package GameAssets;


import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class Player extends GameObject{
    private Animation animationMoveRight;
    private Animation animationMoveLeft;
	private Point2D velocity;
	private boolean canJump;
	
	public Player() {
		super(0,460);
		velocity = new Point2D(0,0);
		canJump = true;
		this.getStyleClass().add("player");
		this.setFitWidth(33);
		this.setFitHeight(46);
		initializeAnimations();
		
	}
	
	public void moveX(boolean movingRight)
	{
			this.setX(this.getX() + (movingRight ? 1 : -1 ));
	}
	
	public void moveY(boolean movingUp)
	{
			this.setY(this.getY() + (movingUp ? -1 : 1));	
			
			if (movingUp)
			{
				animate("up");
			}
			else
			{
				animate("down");
			}
	}
	
	public void animate(String type)
	{
		
		if (type == "right")
			animationMoveRight.play();
		else if (type == "left")
			animationMoveLeft.play();
		else if (type == "up")
			//animationMoveUp.play();
			System.out.println("up");
		else if (type == "down")
			//animationMoveUp.play();
			System.out.println("down");
		else
			return;
	}
	
	private void initializeAnimations()
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
        

        animationMoveRight.play();
	}
	
	public Point2D getVelocity()
	{
		return velocity;
	}
	
	public void setVelocity(Point2D point)
	{
		velocity = point;
	}
	
	public boolean getJump()
	{
		return canJump;
	}
	
	public void setJump(boolean jump)
	{
		this.canJump = jump;
	}
}
