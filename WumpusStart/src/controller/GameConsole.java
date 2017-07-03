package controller;

import java.awt.Point;
import java.util.Scanner;

import model.Map;
import model.Player;
import model.RoomType;
import model.Wumpus;

public class GameConsole {

	Map map;
	boolean gameOver;
	boolean hitWumpus;
	Scanner scanner;
	Player player;
	int checkNextMove;
	Move move;
	Point moveTo;
	Point wumpus;

	public GameConsole() {
		map = new Map();
		player = map.getPlayer();
		gameOver = false;
		hitWumpus = false;
		checkNextMove = 0;
		wumpus = Wumpus.getWumpusLocation();
		scanner = new Scanner(System.in);
	}
	
	public void runGame() {
		
		while(!isGameOver()) {
			System.out.println(map.toString());
	        System.out.print("Move (n, e, s, w, arrow)? ");
	        String playerMove = scanner.next();
	        
	        this.move = new Move(playerMove, player.getPlayerLocation());
	        this.moveTo = move.makeMove();
	        
	        if(moveTo == null) {
				System.out.print("Shoot (n, e, s, w)? ");
				String arrowDirection = scanner.next();
				hitWumpus = player.shootArrow(arrowDirection);
				setGameOver(true);
	        }
	        else {
	        	player.setPlayerLocation(moveTo);
	        }
	        
	        if(map.checkForWumpus()) {
	        	System.out.println("\nI smell something foul, from 1 or 2 rooms away\n");	
	        }
	        if(map.checkForPit())
	        	System.out.println("\nI can hear the wind");
	        	
	        this.wumpus = Wumpus.getWumpusLocation();
	        
	        if(player.getPreviousRoomType() == RoomType.Pit)
	        {
	        	checkNextMove = -1;
	        	setGameOver(true);
	        }
	        
	        if((player.getPlayerLocation().getX() == wumpus.getX()) &&
			    	(player.getPlayerLocation().getY() == wumpus.getY())) {
			    	checkNextMove = -2;
			    	setGameOver(true);
			    }
	 
	        System.out.println();		
		}
		
		System.out.println(map.toString());
	    
	    if(checkNextMove == -1)
	    	System.out.println("You fell in a pit. Game Over");
	    else if(checkNextMove == -2) 
	    	System.out.println("You were eaten by the Wumpus. Game Over");
	    else if(hitWumpus)
	    	System.out.println("Your arrow hit the Wumpus. Good shooting. Game over.");
	    else
	    	System.out.println("Your arrow hit you. Bad shooting. Game over.");
	    
		scanner.close();
	}
	
	
	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
