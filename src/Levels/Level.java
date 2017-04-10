package Levels;

import java.util.ArrayList;
import java.util.Random;

import GameAssets.GameObject;
import GameAssets.ScorePoint;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/*
 * Level class, creates level
 */
public class Level {
	// Initialisation variables
	private Pane content;
	private LevelFactory factory;
	private ArrayList<Integer[][]> platformsCoordinates;
	private ArrayList<Integer[][]> pointsCoordinates;
	private int width;
	private int offset;
	// Platforms and points objects
	private ArrayList<Node> platforms;
	private ArrayList<ScorePoint> points;
	// Gameplay variables
	private String word;

	/*
	 * Constructor for the level
	 * 
	 * @param platformsCoordinates Specifies positions of platforms, using two dimensional array. 
	 * 							   First value is start point at X, second value is an end point at X.
	 * @param pointsCoordinates Specifies positions of points, using two dimensional array.
	 * 							First value specifies X position, second value Y position.
	 * @param word Specifies word goal to be divided into points.
	 */
	public Level(ArrayList<Integer[][]> platformsCoordinates, ArrayList<Integer[][]> pointsCoordinates,String word) {
		this.word = word;
		this.platformsCoordinates = platformsCoordinates;
		this.pointsCoordinates = pointsCoordinates;
		
		points = new ArrayList<ScorePoint>();
		factory = new LevelFactory();
		platforms = new ArrayList<Node>();
		
	}

	/*
	 * Generates level content
	 * 
	 * @return content 
	 */
	public Pane draw(){
		content = new Pane();
		width = platformsCoordinates.get(0)[platformsCoordinates.get(0).length-1][1];
		offset = 0;
		
		
		// Draw background
		initBackground();
		
		// Draw landscape platforms
		initLandscape();
		
		// Draw rest of the platforms
		initPlatforms(1,"grass-half",true);
		
		initPlatforms(2,"grass-half",true);
		
		initPlatforms(3,"brick",false);
		
		// Draw points
		initPoints();
		
		return content;
	}
	
	/*
	 * Initialises level background
	 * 
	 */
	private void initBackground()
	{
		Random generator = new Random();
		
		int sectors = width/3;
		
		for (int i=0; i < 3; i++)
		{
			int next = i+1;

			if (next < 3) {
				for (int j=0; j < 4; j++)
				{
					GameObject cloud = factory.createGameObject("cloud", generator.nextInt(next*sectors-(i*sectors)) + (i*sectors), generator.nextInt(350));
					content.getChildren().add(cloud);
				}
			}
		}
		
	}
	
	/*
	 * Initialises level landscape
	 * 
	 */
	private void initLandscape() 
	{
		for ( int i=0; i < platformsCoordinates.get(0).length; i++){
			
			// Add platforms from given start to end
			for (int j = platformsCoordinates.get(0)[i][0]; j < platformsCoordinates.get(0)[i][1]; j+=35) {
				GameObject platformTop = factory.createGameObject("grass",j, 505);
				GameObject platformBottom = factory.createGameObject("grass-center",j, 535);
				platforms.add(platformTop);
				content.getChildren().add(platformBottom);
				content.getChildren().add(platformTop);
			}
			
			// Check if there's more landscape after gap
			int next = i+1;
			if ( next <= platformsCoordinates.get(0).length-1) {
				// If yes, add water
				for (int j = platformsCoordinates.get(0)[i][1]; j < platformsCoordinates.get(0)[next][0]; j+=35) {
					GameObject platformTop = factory.createGameObject("water",j, 505);
					GameObject platformBottom = factory.createGameObject("water-center",j, 540);
					content.getChildren().add(platformBottom);
					content.getChildren().add(platformTop);
				}
			}
			
		}
	}
	
	/*
	 * Initialises level platforms
	 * 
	 * @param height Specifies height where the platform will be placed i.e. Y position of platform.
	 * @param type Specifies type of platform.
	 * @param directed Specifies if platform has different appearance for start and end.
	 */
	private void initPlatforms(int height,String type, boolean directed)
	{
		int positionY = 0;
		
		switch(height) 
		{
			case 0:
				break;
			case 1: positionY = 400;
				break;
			case 2: positionY = 350;
				break;
			case 3: positionY = 300;
				break;
			default: 
				break;
		}
		for ( int i=0; i < this.platformsCoordinates.get(height).length; i++){
			
			// Add platforms from given start to end
			for (int j = platformsCoordinates.get(height)[i][0]; j < platformsCoordinates.get(height)[i][1]; j+=35) {
				String localType = type;
				if (directed) 
				{
				if (j == platformsCoordinates.get(height)[i][0])
				{
					localType+="-left";
						
				}
				else if (j+35 >= platformsCoordinates.get(height)[i][1])
				{
					localType+="-right";
				}
				else
				{
					localType+="-mid";
				}
				}
				
				GameObject platformTop = factory.createGameObject(localType,j, positionY);
				platforms.add(platformTop);
				content.getChildren().add(platformTop);
			}
		}
	}
	
	/*
	 * Initialises score points
	 * 
	 */
	private void initPoints()
	{
		for (int i=0; i < pointsCoordinates.get(0).length; i++)
		{
			ScorePoint point = (ScorePoint) factory.createGamePoint(word.charAt(i)+"",pointsCoordinates.get(0)[i][0],pointsCoordinates.get(0)[i][1]);
			points.add(point);
			content.getChildren().add(point);
		}
	}
	
	/*
	 * Returns level width
	 * 
	 * @return width
	 */
	public int getWidth(){
		return width;
	}

	/*
	 * Returns level offset
	 * 
	 * @return offset
	 */
	public int getOffset()
	{
		return offset;
	}
	
	/* Return level word goal
	 * 
	 * @return word
	 */
	public String getWord()
	{
		return word;
	}
	
	/*
	 * Returns array of platforms specifying their positions
	 * 
	 * @return platforms
	 */
	public ArrayList<Node> getPlatforms(){
		return platforms;
	}
	
	/*
	 * Returns array of points specifying their positions
	 * 
	 * @return points
	 */
	public ArrayList<ScorePoint> getPoints(){
		return points;
	}
	
	/*
	 * Sets level offset
	 * 
	 * @param value Specifies new value of level offset.
	 */
	public void setOffset(int value)
	{
		offset = value;
	}
	
	/*
	 * Updates score
	 */
	public void updateScore()
	{
		for (int i = getPoints().size() - 1; i >= 0; i--) {
		    if (getPoints().get(i).isCollected()) { 
		        getPoints().remove(i);
		    }
		}
	}
	
}
