package Levels;

import java.util.ArrayList;

import GameAssets.GameObject;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Level {
	private Pane content;
	private LevelFactory factory;
	private int[][] landscapePlatforms;
	private int[][] firstHeightPlatforms;
	private int[][] secondHeightPlatforms;
	private int width;
	private ArrayList<Node> platforms;

	public Level(int[][] landscapePlatforms, int[][] firstHeightPlatforms, int[][] secondHeightPlatforms)
	{
		this.landscapePlatforms = landscapePlatforms;
		this.firstHeightPlatforms = firstHeightPlatforms;
		this.secondHeightPlatforms = secondHeightPlatforms;
		factory = new LevelFactory();
		platforms = new ArrayList<Node>();
	}
	
	public Pane draw(){
		content = new Pane();
		width = 1200;
		
		// Adding landscape platforms
		for ( int i=0; i < this.landscapePlatforms.length; i++){
			Node platform = factory.createPlatform("landscape", landscapePlatforms[i][0], landscapePlatforms[i][1], 550);
			platforms.add(platform);
			content.getChildren().add(platform);
		}
		
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
		
		return content;
	}
	
	public int getWidth(){
		return width;
	}
	
	public ArrayList<Node> getPlatforms(){
		return platforms;
	}
}
