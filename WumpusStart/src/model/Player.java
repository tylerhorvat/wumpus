package model;

import java.awt.Point;

public class Player {

	Point playerLocation;
	RoomType roomType = RoomType.Hunter;
	RoomType previousRoomType;
	
	public Point getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Point playerLocation) {
		
		this.playerLocation = playerLocation;
	}

	public Player () {
		
	}
	
	public void makeMove(Point moveTo) {
		
		
		
	}
	
	public void shootArrow (String s) {
		public boolean hitPlayer = false;
		int r, c;
		
		if(s == "n") {
			while(!hitPlayer) {
				r = (int) this.getPlayerLocation().getX();
				c = (int) this.getPlayerLocation().getY();
			}
		}
	}
	
	
}
