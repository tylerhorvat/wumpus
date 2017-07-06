/*
 * Written by: Tyler Horvat
 * CSC 335 Summer 2017
 */

package model;

import java.awt.Point;

public class Player {

	Point playerLocation;
	RoomType roomType = RoomType.Hunter;
	RoomType previousRoomType;
	Map map;
	
	public Point getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Point point, Room[][] map) {
		//this.map = map;
		
		if(this.previousRoomType == RoomType.Hunter)
			map[this.playerLocation.x][this.playerLocation.y].setRoomType(RoomType.Empty);
		if(this.previousRoomType != RoomType.Hunter)
			map[this.playerLocation.x][this.playerLocation.y].setRoomType(this.previousRoomType);
		
		this.previousRoomType = map[point.x][point.y].getRoomType();
		
		map[point.x][point.y].setIsVisited();
		
		this.playerLocation = point;
		map[point.x][point.y].setRoomType(this.roomType);
	}

	public Player (Point p, Map map) {
		this.previousRoomType = RoomType.Hunter;
		this.playerLocation = p;
		this.map = map;
	}
	
	public RoomType getPreviousRoomType() {
		return previousRoomType;
	}

	//true for hit wumpus, false for hit player
	public boolean shootArrow (String s) {
		
		int r, c;
		Point wumpus = map.getWumpus().getWumpusLocation(); 
		
		
		if(s.equals("n") || s.equals("s")) {
				//r = (int) this.getPlayerLocation().getX();
				c = (int) this.getPlayerLocation().getX();
				int w = (int) wumpus.getX();
				if(c != w)
					return false;
		}
		else if (s.equals("w") || s.equals("e")) {
			r = (int) this.getPlayerLocation().getY();
			int w = (int) wumpus.getY();
			if(r != w)
				return false;
		}
		return true;
	}
	
}
