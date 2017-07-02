package model;

import java.awt.Point;

public class Player {

	Point playerLocation;
	RoomType roomType = RoomType.Hunter;
	RoomType previousRoomType;
	
	public Point getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Point point) {
		Room [][] map = Map.getMap();
		
		if(this.previousRoomType == RoomType.Hunter)
			map[this.playerLocation.x][this.playerLocation.y].setRoomType(RoomType.Empty);
		if(this.previousRoomType != RoomType.Hunter)
			map[this.playerLocation.x][this.playerLocation.y].setRoomType(this.previousRoomType);
		
		this.previousRoomType = map[point.x][point.y].getRoomType();
		
		map[point.x][point.y].setIsVisited();
		
		this.playerLocation = point;
		map[point.x][point.y].setRoomType(this.roomType);
	}

	public Player (Point p) {
		this.previousRoomType = RoomType.Hunter;
		this.playerLocation = p;
	}
	
	public RoomType getPreviousRoomType() {
		return previousRoomType;
	}

	//true for hit wumpus, false for hit player
	public boolean shootArrow (String s) {
		
		int r, c;
		Point wumpus = Wumpus.getWumpusLocation();
		
		
		if(s.equals("n") || s.equals("s")) {
				//r = (int) this.getPlayerLocation().getX();
				c = (int) this.getPlayerLocation().getY();
				int w = (int) wumpus.getY();
				if(c != w)
					return false;
		}
		else if (s.equals("w") || s.equals("e")) {
			r = (int) this.getPlayerLocation().getX();
			int w = (int) wumpus.getX();
			if(r != w)
				return false;
		}
		return true;
	}
	
}
