
/*
 * Written by: Tyler Horvat
 * CSC 335 Summer 2017
 */
package model;

import java.awt.Point;
import java.util.Random;

public class Map {

	final static int MAX_SIZE = 12;
	private Room [][] map;
	Player player;
	
	public Room[][] getMap() {
		return map;
	}
	
	public boolean checkForPit() {
		boolean pitCheck = false;
		
		if(player.previousRoomType == RoomType.Slime ||
		   player.previousRoomType == RoomType.Goop)
			pitCheck = true;
		
		return pitCheck;
		
	}
	
	public boolean checkForWumpus() {
		Point wumpusPoint = wumpus.getWumpusLocation();
		Point hunterPoint = player.getPlayerLocation();
		
		if((wumpusPoint.getX() == hunterPoint.getX() + 1  ||
		    wumpusPoint.getX() == hunterPoint.getX() + 2) &&
			wumpusPoint.getY() == hunterPoint.getY())  {
				return true;
		}
		if((wumpusPoint.getX() == hunterPoint.getX() - 1  ||
			wumpusPoint.getX() == hunterPoint.getX() - 2) &&
			wumpusPoint.getY() == hunterPoint.getY())  {
				return true;
		}
		if((wumpusPoint.getY() == hunterPoint.getY() - 1  ||
			wumpusPoint.getY() == hunterPoint.getY() - 2) &&
			wumpusPoint.getX() == hunterPoint.getX())  {
				return true;
		}
		if((wumpusPoint.getY() == hunterPoint.getY() + 1  ||
			wumpusPoint.getY() == hunterPoint.getY() + 2) &&
			wumpusPoint.getX() == hunterPoint.getX())  {
				return true;
			}
		return false;
	}

	Wumpus wumpus;
	
	public Map() {
		initializeMap();
		initializeWumpus();
		initializeSlimePits();
		initializeBlood();
		initializeHunter();
	}
	
	/*public boolean checkForWumpus() {
		Point point = wumpus.getWumpusLocation();
		return false;
	}*/
	
	public Player getPlayer() {
		return player;
	}

	public void initializeHunter() {
		Random randomNum = new Random();
		
		boolean empty = true;
		
		while(empty) {
			int r = randomNum.nextInt(12);
			int c = randomNum.nextInt(12);
			if(map[r][c].roomType == RoomType.Empty){
				this.player = new Player(new Point(r, c), this);
				map[r][c].roomType = player.roomType;
				map[r][c].setIsVisited();
				empty = false;
			}
		}
	}
	
	public void initializeSlimePits() {
		Random randomNum = new Random();
		int numberOfSlimePits = randomNum.nextInt(3) + 3;
		
		for(int i = 0; i < numberOfSlimePits; i++) {
			boolean empty = true;
			
			while(empty) {
				int r = randomNum.nextInt(12);
				int c = randomNum.nextInt(12);
				
				if(map[r][c].roomType == RoomType.Empty) {
					map[r][c].roomType = RoomType.Pit;
					empty = false;
					
					if(map[checkIndex(r + 1)][c].roomType == RoomType.Empty)
						map[checkIndex(r + 1)][c].roomType = RoomType.Slime;
					if(map[checkIndex(r - 1)][c].roomType == RoomType.Empty)
						map[checkIndex(r - 1)][c].roomType = RoomType.Slime;
					if(map[r][checkIndex(c + 1)].roomType == RoomType.Empty)
						map[r][checkIndex(c + 1)].roomType = RoomType.Slime;
					if(map[r][checkIndex(c - 1)].roomType == RoomType.Empty)
						map[r][checkIndex(c - 1)].roomType = RoomType.Slime;
				}		
			}
		}
	}
	
	public void initializeBlood() {
		int r = (int) wumpus.getWumpusLocation().getX();
		int c = (int) wumpus.getWumpusLocation().getY();
		
		int B1 = checkIndex(r - 1);
		int B2 = checkIndex(r - 2);
		int B3 = checkIndex(r + 1);
		int B4 = checkIndex(r + 2);
		
		int B5 = checkIndex(c - 1);
		int B6 = checkIndex(c - 2);
		int B7 = checkIndex(c + 1);
		int B8 = checkIndex(c + 2);
		
		if(map[B1][c].roomType == RoomType.Slime)
			map[B1][c].roomType = RoomType.Goop;
		else if(map[B1][c].roomType == RoomType.Empty)
			map[B1][c].roomType = RoomType.Blood;
		if(map[B2][c].roomType == RoomType.Empty)
			map[B2][c].roomType = RoomType.Blood;
		else if(map[B2][c].roomType == RoomType.Slime)
			map[B2][c].roomType = RoomType.Goop;
		if(map[B3][c].roomType == RoomType.Slime)
			map[B3][c].roomType = RoomType.Goop;
		else if(map[B3][c].roomType == RoomType.Empty)
			map[B3][c].roomType = RoomType.Blood;
		if(map[B4][c].roomType == RoomType.Slime)
			map[B4][c].roomType = RoomType.Goop;
		else if(map[B4][c].roomType == RoomType.Empty)
			map[B4][c].roomType = RoomType.Blood;
		
		if(map[r][B5].roomType == RoomType.Slime)
			map[r][B5].roomType = RoomType.Goop;
		else if(map[r][B5].roomType == RoomType.Empty)
			map[r][B5].roomType = RoomType.Blood;
		if(map[r][B6].roomType == RoomType.Empty)
			map[r][B6].roomType = RoomType.Blood;
		else if(map[r][B6].roomType == RoomType.Slime)
			map[r][B6].roomType = RoomType.Goop;
		if(map[r][B7].roomType == RoomType.Slime)
			map[r][B7].roomType = RoomType.Goop;
		else if(map[r][B7].roomType == RoomType.Empty)
			map[r][B7].roomType = RoomType.Blood;
		if(map[r][B8].roomType == RoomType.Slime)
			map[r][B8].roomType = RoomType.Blood;
		else if(map[r][B8].roomType == RoomType.Empty)
			map[r][B8].roomType = RoomType.Blood;
		
		if(map[B1][B5].roomType == RoomType.Slime)
			map[B1][B5].roomType = RoomType.Goop;
		else if(map[B1][B5].roomType == RoomType.Empty)
			map[B1][B5].roomType = RoomType.Blood;
		if(map[B3][B5].roomType == RoomType.Empty)
			map[B3][B5].roomType = RoomType.Blood;
		else if(map[B3][B5].roomType == RoomType.Slime)
			map[B3][B5].roomType = RoomType.Goop;
		if(map[B1][B7].roomType == RoomType.Slime)
			map[B1][B7].roomType = RoomType.Goop;
		else if(map[B1][B7].roomType == RoomType.Empty)
			map[B1][B7].roomType = RoomType.Blood;
		if(map[B3][B7].roomType == RoomType.Slime)
			map[B3][B7].roomType = RoomType.Blood;
		else if(map[B3][B7].roomType == RoomType.Empty)
			map[B3][B7].roomType = RoomType.Blood;
		
	}

	public void initializeWumpus() {
		wumpus = new Wumpus();
		map[(int) wumpus.getWumpusLocation().getX()][(int) wumpus.getWumpusLocation().getY()].setRoomType(RoomType.Wumpus);
		
	}
	
	public Wumpus getWumpus() {
		return wumpus;
	}

	public Room[][] initializeMap() {
		map = new Room [MAX_SIZE][MAX_SIZE];
		for(int i = 0; i < MAX_SIZE; i++) {
			for(int j = 0; j < MAX_SIZE; j++) {
				map[i][j] = new Room();
			}
		}
		return map;
	}
	
	  @Override
	  public String toString() {
	    String result = "";
	    for (int r = 0; r < MAX_SIZE; r++) {
	      for (int c = 0; c < MAX_SIZE; c++) {
	    	  result += " " + map[r][c] + " ";
	      }
	      result += "\n";
	    }
	    return result;
	  }
	
	public static int checkIndex(int index) {
		if(index < 0)
			return index + MAX_SIZE;
		else if(index > MAX_SIZE - 1)
			return index % MAX_SIZE;
		else
			return index;
	}

}
