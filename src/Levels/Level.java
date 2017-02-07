package Levels;

import java.util.ArrayList;
import java.util.Random;

import GameAssets.GameObject;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Level {
	private Pane content;
	private LevelFactory factory;
	private Integer[][] landscapePlatforms;
	private Integer[][] firstHeightPlatforms;
	private Integer[][] secondHeightPlatforms;
	private int width;
	private ArrayList<GameObject> platforms;

	public Level(Integer[][] landscapePlatforms, Integer[][] firstHeightPlatforms, Integer[][] secondHeightPlatforms)
	{
		this.landscapePlatforms = landscapePlatforms;
		this.firstHeightPlatforms = firstHeightPlatforms;
		this.secondHeightPlatforms = secondHeightPlatforms;
		factory = new LevelFactory();
		platforms = new ArrayList<GameObject>();
	}
	
	public Pane draw(){
		content = new Pane();
		width = 1800;
		
		// Generating background
		
		Random generator = new Random();
		
		int sectors = width/3;
		
		for (int i=0; i < 3; i++)
		{
			int next = i+1;

			System.out.println(next*sectors + " " + i*sectors);
			if (next < 3) {
				for (int j=0; j < 4; j++)
				{
					GameObject cloud = factory.createGameObject("cloud", generator.nextInt(next*sectors-(i*sectors)) + (i*sectors), generator.nextInt(350));
					content.getChildren().add(cloud);
				}
			}
		}
		
		
		// Adding landscape platforms
		for ( int i=0; i < this.landscapePlatforms.length; i++){
			
			// Add platforms from given start to end
			for (int j = landscapePlatforms[i][0]; j < landscapePlatforms[i][1]; j+=35) {
				GameObject platformTop = factory.createGameObject("landscape",j, 505);
				GameObject platformBottom = factory.createGameObject("landscape-center",j, 535);
				platforms.add(platformTop);
				content.getChildren().add(platformBottom);
				content.getChildren().add(platformTop);
			}
			
			// Check if there's more landscape after a gap
			int next = i+1;
			if ( next <= landscapePlatforms.length-1) {
				for (int j = landscapePlatforms[i][1]; j < landscapePlatforms[next][0]; j+=35) {
					GameObject platformTop = factory.createGameObject("water",j, 505);
					GameObject platformBottom = factory.createGameObject("water-center",j, 540);
					content.getChildren().add(platformBottom);
					content.getChildren().add(platformTop);
				}
			}
			
		}
		
		System.out.println("Sixth platform X:" + platforms.get(9).getTranslateX());
		
		/*
		
		// Adding first height platforms
		for ( int i=0; i < this.firstHeightPlatforms.length; i++){
			Node platform = factory.createPlatform("wooden", firstHeightPlatforms[i][0], firstHeightPlatforms[i][1], 450);
			platforms.add(platform);
			content.getChildren().add(platform);
		}
		
		// Adding second height platforms
		for ( int i=0; i < this.secondHeightPlatforms.length; i++){
			Node platform = factory.createPlatform("cloud", secondHeightPlatforms[i][0], secondHeightPlatforms[i][1], 350);
			platforms.add(platform);
			content.getChildren().add(platform);
		}
		
		*/
		
		return content;
	}
	
	public int getWidth(){
		return width;
	}
	
	public ArrayList<GameObject> getPlatforms(){
		return platforms;
	}
}
