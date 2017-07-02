package model;

import java.util.Random;

import java.awt.Point;

public class Wumpus {

	Point wumpusLocation;
	
	Wumpus() {
		setWumpusLocation();
	}

	public Point getWumpusLocation() {
		return wumpusLocation;
	}

	private void setWumpusLocation() {
		
		Random randomNum = new Random ();
		int rowIndex = randomNum.nextInt(12);
		int colIndex = randomNum.nextInt(12);
		this.wumpusLocation = new Point(rowIndex, colIndex);
	}
	
	
}
