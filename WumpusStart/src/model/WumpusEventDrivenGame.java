/*
 * Written by: Tyler Horvat
 * CSC 335 Summer 2017
 */

package model;

import java.awt.Point;
import java.util.Observable;
import controller.Move;

public class WumpusEventDrivenGame extends Observable {
	
	Map map;
	Room [][] cave;
	Player player;
	Wumpus wumpus;
	boolean gameOver;
	boolean hitWumpus;
	int checkNextMove;
	Move move;
	Point moveTo;
	Point wumpusLocation;
	String playerMove;
	String arrowDirection;

	public WumpusEventDrivenGame() {
		
	}
	
	public void startNewGame() {
		map = new Map();
		player = map.getPlayer();
		wumpus = map.getWumpus();
		gameOver = false;
		hitWumpus = false;
		wumpusLocation = wumpus.getWumpusLocation();
		playerMove = null;
		setChanged();
		notifyObservers();
	}
	
	public void makeAMove(String s) {
		this.move = new Move(s, player.getPlayerLocation());
        this.moveTo = move.makeMove();
        player.setPlayerLocation(moveTo, map.getMap());
        
        if(player.getPreviousRoomType() == RoomType.Pit)
        {
        	checkNextMove = -1;
        	setGameOver(true);
        }
        
        if((player.getPlayerLocation().getX() == wumpusLocation.getX()) &&
		    	(player.getPlayerLocation().getY() == wumpusLocation.getY())) {
		    	checkNextMove = -2;
		    	setGameOver(true);
        }
        setChanged();
        notifyObservers();
	}
	
	public void shootAnArrow(String s) {
		hitWumpus = player.shootArrow(s);
		setGameOver(true);
		setChanged();
		notifyObservers();
		
	}

	public String checkGameState() {		
		String result = "";
		if(hitWumpus)
	    	result += "Your arrow hit the Wumpus. Good shooting. Game over.";
		else if(map.checkForWumpus()) 
        	result +="I smell something foul, from 1 or 2 rooms away";	
		else if(map.checkForPit())
        	result += "I can hear the wind";
		else if(checkNextMove == -1) 
        	result += "You fell in a pit. Game Over";
		else if(checkNextMove == -2) 
        	result += "You walked into the Wumpus Room. Game Over.";
	    else if(!hitWumpus && isGameOver())
	    	result += "Your arrow hit you. Bad shooting. Game over.";
	    else
	    	result += "You are in the clear";
		
		notifyObservers();
		return result;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		notifyObservers();
	}

	public Point getWumpusLocation() {
		return wumpusLocation;
	}

	public Wumpus getWumpus() {
		return wumpus;
	}

	public Map getMap() {
		return map;
	}
}
