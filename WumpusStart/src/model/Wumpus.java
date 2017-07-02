package model;

import java.util.Random;

import java.awt.Point;

public class Wumpus {

	static Point wumpusLocation;
	
	Wumpus() {
		setWumpusLocation();
	}

	public static Point getWumpusLocation() {
		return wumpusLocation;
	}

	private void setWumpusLocation() {
		
		Random randomNum = new Random ();
		int rowIndex = randomNum.nextInt(12);
		int colIndex = randomNum.nextInt(12);
		Wumpus.wumpusLocation = new Point(rowIndex, colIndex);
	}
}
