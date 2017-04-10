package Levels;

import java.util.ArrayList;

/*
 * Class containing all game levels.
 */
public class Levels{
	
	/*
	 * Returns level.
	 * 
	 * @param levelNo Specifies number of level to be returned.
	 * @return Level 
	 */
	public static Level getLevel(int levelNo)
	{
		if( levelNo == 1)
			return new Level( new ArrayList<Integer[][]>() {{
					add(new Integer[][] {{0,420}, {560,665}, {885,1025}, {1130,1800}});
					add(new Integer[][] {{175,350}, {700,800}});
					add(new Integer[][] {{400,505},{850,1050}});
					add(new Integer[][] {{0,170}, {535,605}, {1100,1250}});
			}}, new ArrayList<Integer[][]>() {{
				add(new Integer[][] {{50,240},{590,450}, {550,240}, {1350,240}});
			}},"bear");
		else if (levelNo == 2)
			return new Level( new ArrayList<Integer[][]>() {{
				add(new Integer[][] {{0,455}, {525,665}, {885,1025}, {1130,1800}});
				add(new Integer[][] {{110,180}, {700,800},{1050,1120}});
				add(new Integer[][] {{215,285}});
				add(new Integer[][] {{355,605}, {850,1250}});
		}}, new ArrayList<Integer[][]>() {{
			add(new Integer[][] {{450,240},{560,460},{1070,350}});
		}},"cat");
		else if (levelNo == 3)
			return new Level( new ArrayList<Integer[][]>() {{
				add(new Integer[][] {{0,245}, {455,665}, {1130,2200}});
				add(new Integer[][] {{330,400},{700,770},{835,905},{950,1020},{1360,1430}});
				add(new Integer[][] {{1200,1340}});
				add(new Integer[][] {{420,595},{1450,1520},{1640,1710}});
		}}, new ArrayList<Integer[][]>() {{
			add(new Integer[][] {{345,350},{500,460},{850,350},{1470,240},{1660,240}});
		}},"plane");
		else if (levelNo == 4)
			return new Level( new ArrayList<Integer[][]>() {{
				add(new Integer[][] {{0,140},{1030,2000}});
				add(new Integer[][] {{170,240},{360,430},{610,680},{1200,1270}});
				add(new Integer[][] {{1400,1540}});
				add(new Integer[][] {{720,825},{1100,1170},{1635,1670}});
		}}, new ArrayList<Integer[][]>() {{
			add(new Integer[][] {{630,350},{950,350},{1060,200},{1700,200}});
		}},"yeti");
		else 
			return null;
	}
}
