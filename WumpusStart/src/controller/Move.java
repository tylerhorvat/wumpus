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
	
		if(this.move == "arrow") {
			return null;
			/*Scanner scanner = new Scanner(System.in);
			System.out.print("Shoot (n, e, s, w)? ");
			String arrowDirection = scanner.next();*/
		}
		else if(this.move == "n"){
			r = Map.checkIndex(playerCurrentLocation.x + 1);
		    c = Map.checkIndex(playerCurrentLocation.y);
		}
		else if(this.move == "e") {
			r = Map.checkIndex(playerCurrentLocation.x);
			c = Map.checkIndex(playerCurrentLocation.y + 1);
		}
		else if(this.move == "s") {
			r = Map.checkIndex(playerCurrentLocation.x - 1);
			c = Map.checkIndex(playerCurrentLocation.y);
		}
		else if(this.move == "w") {
			r = Map.checkIndex(playerCurrentLocation.x);
			c = Map.checkIndex(playerCurrentLocation.y - 1);
		}
		
		return new Point(r, c);
			
	}
	
	public boolean checkGameOver() {
		
		return true;
	}
}
