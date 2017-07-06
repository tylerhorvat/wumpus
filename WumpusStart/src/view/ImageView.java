/*
 * Written by: Tyler Horvat
 * CSC 335 Summer 2017
 */

package view;

import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Room;
import model.RoomType;
import model.WumpusEventDrivenGame;

public class ImageView extends BorderPane implements Observer {
	
	private WumpusEventDrivenGame wumpusGame;
	private Button north;
	private Button south;
	private Button east;
	private Button west;
	private GridPane buttonPanel;
	private GridPane panel;
	private Label gameState;
	Room[][] cave;
	GraphicsContext gc;
	Image blood, goop, ground, slime, slimepit, hunter, wumpus;
	
	public ImageView(WumpusEventDrivenGame wumpusGame) {
		this.wumpusGame = wumpusGame;
		cave = wumpusGame.getMap().getMap();
		gameState = new Label (wumpusGame.checkGameState());
		gameState.setFont(new Font("Comic Sans", 18));
		gameState.setTextFill(Color.web("#0072a6"));
		MovementListener arrowKey = new MovementListener();
		this.setOnKeyPressed(arrowKey);
		
		setUpButtons();
		
		Canvas canvas = new Canvas(600, 600);
	    // use false to ensure the images are created before drawImage
	    blood = new Image("file:WumpusStart/images/Blood.png", false);
	    goop = new Image("file:WumpusStart/images/Goop.png", false);
	    ground = new Image("file:WumpusStart/images/Ground.png", false);
	    slime = new Image("file:WumpusStart/images/Slime.png", false);
	    slimepit = new Image("file:WumpusStart/images/Slimepit.png", false);
	    hunter = new Image("file:WumpusStart/images/TheHunter.png", false);
	    wumpus = new Image("file:WumpusStart/images/wumpus.png", false);
	   
	    gc = canvas.getGraphicsContext2D();
	    // Set a black background
	    gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, 600, 600);

	    initializeCanvas();   
	    
		
		this.setTop(canvas);
		
		panel = new GridPane();
		gameState.setAlignment(Pos.TOP_CENTER);
		buttonPanel.setAlignment(Pos.BOTTOM_CENTER);
		
		panel.add(gameState, 1, 0);
		panel.add(new Label(""), 1, 2);
		panel.add(buttonPanel, 1, 3);
		panel.setAlignment(Pos.BOTTOM_CENTER);
		
		this.setBottom(panel);
		
	}
	private void showAll() {
		int size = 12;
		gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, 600, 600);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				//textFields[i][j].setTextFill(Color.web("#0076a3"));
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					gc.drawImage(hunter, 50*i, 50*j);
				if(cave[i][j].isVisited() == false)
					gc.drawImage(ground, 50*i, 50*j);
				if(cave[i][j].getRoomType() == RoomType.Wumpus)
					gc.drawImage(wumpus, 50*i, 50*j);
				if(cave[i][j].getRoomType() == RoomType.Slime)
					gc.drawImage(slime, 50*i, 50*j);
				if(cave[i][j].getRoomType() == RoomType.Blood)
					gc.drawImage(blood, 50*i, 50*j);
				if(cave[i][j].getRoomType() == RoomType.Goop)
					gc.drawImage(goop, 50*i, 50*j);
				if(cave[i][j].getRoomType() == RoomType.Pit)
					gc.drawImage(slimepit, 50*i, 50*j);
				/*else if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == false)
					textFields[i][j].setText("O");*/
			}
		}
	}
	private void updateCanvas() {
		int size = 12;
		gc.setFill(Color.BLACK);
	    gc.fillRect(0, 0, 600, 600);
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				//textFields[i][j].setTextFill(Color.web("#0076a3"));
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					gc.drawImage(hunter, 50*i, 50*j);
				else if(cave[i][j].isVisited() == false)
					gc.drawImage(ground, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Wumpus)
					gc.drawImage(wumpus, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Slime)
					gc.drawImage(slime, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Blood)
					gc.drawImage(blood, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Goop)
					gc.drawImage(goop, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Pit)
					gc.drawImage(slimepit, 50*i, 50*j);
				/*else if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == false)
					textFields[i][j].setText("O");*/
			}
		}
	}
	
	private void initializeCanvas() {
		int size = 12;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					gc.drawImage(hunter, 50*i, 50*j);
				else if(cave[i][j].isVisited() == false)
					gc.drawImage(ground, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Wumpus)
					gc.drawImage(wumpus, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Slime)
					gc.drawImage(slime, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Blood)
					gc.drawImage(blood, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Goop)
					gc.drawImage(goop, 50*i, 50*j);
				else if(cave[i][j].getRoomType() == RoomType.Pit)
					gc.drawImage(slimepit, 50*i, 50*j);
				/*else if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == false)
					textFields[i][j].setText("O");*/
			}
		}
	}
	

	
	private void setUpButtons() {
		ArrowListener arrowHandler = new ArrowListener();
		
		buttonPanel = new GridPane();
		buttonPanel.setMinHeight(45);
		buttonPanel.setMinWidth(45);
		
		north = new Button();
		north.setText("N");
		north.setOnAction(arrowHandler);
		north.setMinHeight(15);
		north.setMinWidth(15);
		
		
		south = new Button();
		south.setText("S");
		south.setOnAction(arrowHandler);
		south.setMinWidth(16);
		south.setMinHeight(16);
		
		east = new Button();
		east.setText("E");
		east.setOnAction(arrowHandler);
		east.setMinWidth(15);
		east.setMinHeight(15);
		
		west = new Button();
		west.setText("W");
		west.setOnAction(arrowHandler);
		west.setMinWidth(15);
		west.setMinHeight(15);
		
		buttonPanel.add(north, 1, 0);
		buttonPanel.add(south, 1, 2);
		buttonPanel.add(east, 2, 1);
		buttonPanel.add(west, 0, 1);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		wumpusGame = (WumpusEventDrivenGame) arg0;
		
		//updateBoard();
		gameState.setText(wumpusGame.checkGameState());
		updateCanvas();
		if(wumpusGame.isGameOver())
			showAll();
		
	}
	
	private class MovementListener implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(!wumpusGame.isGameOver()) {
				if(arg0.getCode() == KeyCode.DOWN) {
					wumpusGame.makeAMove("e");
				}
				else if(arg0.getCode() == KeyCode.RIGHT) {
					wumpusGame.makeAMove("s");
				}
				else if(arg0.getCode() == KeyCode.UP) {
					wumpusGame.makeAMove("w");
				}
				else if(arg0.getCode() == KeyCode.LEFT) {
					wumpusGame.makeAMove("n");
				}
			}
			gameState.setText(wumpusGame.checkGameState());
		}
		
	}
	
	private class ArrowListener implements EventHandler<ActionEvent> {
		 
		@Override
		public void handle(ActionEvent arg0) {
			Button buttonClicked = (Button) arg0.getSource();
			
			//if(!wumpusGame.isGameOver()) {
				
				if(buttonClicked == north) {
					wumpusGame.shootAnArrow("n");
				}
				else if(buttonClicked == south) {
					wumpusGame.shootAnArrow("s");
				}
				else if(buttonClicked == east) {
					wumpusGame.shootAnArrow("e");
				}
				else if(buttonClicked == west) {
					wumpusGame.shootAnArrow("w");
				}
				
			
			gameState.setText(wumpusGame.checkGameState());

		}
	}
}
