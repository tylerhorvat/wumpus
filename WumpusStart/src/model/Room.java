package model;

public class Room {
	
	RoomType roomType;
	boolean isVisited;
	
	Room() {
		this.roomType = RoomType.Empty;
		isVisited = false;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	public void setIsVisited() {
		this.isVisited = true;
	}
	
	public boolean isVisited() {
		return this.isVisited();
		
	}

	public String toString() {
		if(roomType == RoomType.Hunter)
			return "O";
		if(this.isVisited == false)
			return "X";
		if(roomType == RoomType.Wumpus)
			return "W";
		if(roomType == RoomType.Slime)
			return "S";
		if(roomType == RoomType.Blood)
			return "B";
		if(roomType == RoomType.Goop)
			return "G";
		if(roomType == RoomType.Pit)
			return "P";
		if(roomType == RoomType.Empty)
			return "X";
		return " ";
	}
	
}
