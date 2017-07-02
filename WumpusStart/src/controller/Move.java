package controller;

import java.awt.Point;
import java.util.Scanner;

import model.Map;

public class Move {
    
	String move;
	Point playerCurrentLocation;
	
	public Move(String s, Point playerCurrentLocation) {
		this.move = s;
		this.playerCurrentLocation = playerCurrentLocation;
		
	}
	
	public Point makeMove() {
		int r = 0, c = 0;
	
		if(this.move.equals("arrow")) {
			return null;
		
		}
		else if(this.move.equals("n")){
			r = Map.checkIndex(playerCurrentLocation.x - 1);
		    c = Map.checkIndex(playerCurrentLocation.y);
		}
		else if(this.move.equals("e")) {
			r = Map.checkIndex(playerCurrentLocation.x);
			c = Map.checkIndex(playerCurrentLocation.y + 1);
		}
		else if(this.move.equals("s")) {
			r = Map.checkIndex(playerCurrentLocation.x + 1);
			c = Map.checkIndex(playerCurrentLocation.y);
		}
		else if(this.move.equals("w")) {
			r = Map.checkIndex(playerCurrentLocation.x);
			c = Map.checkIndex(playerCurrentLocation.y - 1);
		}
		
		return new Point(r, c);
			
	}

}
