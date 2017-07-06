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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Room;
import model.RoomType;
import model.WumpusEventDrivenGame;


public class TextView extends BorderPane implements Observer {
	
	WumpusEventDrivenGame wumpusGame;
	private Button north;
	private Button south;
	private Button east;
	private Button west;
	private GridPane buttonPanel;
	private GridPane panel;
	private Label gameState;
	Room[][] cave;
	private Label[][] textFields = null;
	private GridPane gamePanel;
	
	public TextView(WumpusEventDrivenGame wumpusGame) {
		this.wumpusGame = wumpusGame;
		cave = wumpusGame.getMap().getMap();
		gameState = new Label (wumpusGame.checkGameState());
		gameState.setFont(new Font("Comic Sans", 18));
		gameState.setTextFill(Color.web("#0072a6"));
		setUpButtons();
		movementHandler arrowKey = new movementHandler();
		
		gamePanel = new GridPane();
		gamePanel.setHgap(10);
		gamePanel.setVgap(10);
		gamePanel.setMinWidth(600);
		gamePanel.setMinHeight(600);
		gamePanel.setAlignment(Pos.CENTER);
		//gamePanel.setOnKeyPressed(arrowKey);
		
		initializeLabelPanel();
		
		this.setTop(gamePanel);
		
		panel = new GridPane();
		gameState.setAlignment(Pos.TOP_CENTER);
		buttonPanel.setAlignment(Pos.BOTTOM_CENTER);
		
		panel.add(gameState, 1, 0);
		panel.add(new Label(""), 1, 2);
		panel.add(buttonPanel, 1, 3);
		panel.setAlignment(Pos.BOTTOM_CENTER);
		
		this.setBottom(panel);
		this.setOnKeyPressed(arrowKey);
		
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
	
	private void showAll() {
		int size = 12;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				
				if(cave[i][j].isVisited() == false)
					textFields[i][j].setText("X");
				if(cave[i][j].getRoomType() == RoomType.Wumpus)
					textFields[i][j].setText("W");
				if(cave[i][j].getRoomType() == RoomType.Slime)
					textFields[i][j].setText("S");
				if(cave[i][j].getRoomType() == RoomType.Blood)
					textFields[i][j].setText("B");
				if(cave[i][j].getRoomType() == RoomType.Goop)
					textFields[i][j].setText("G");
				if(cave[i][j].getRoomType() == RoomType.Pit)
					textFields[i][j].setText("P");
				if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == true)
					textFields[i][j].setText(" ");
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					textFields[i][j].setText("O");
				
			}
		}
	}
	
	private void updateLabelPanel() {
		int size = 12;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					textFields[i][j].setText("O");
				else if(cave[i][j].isVisited() == false)
					textFields[i][j].setText("X");
				else if(cave[i][j].getRoomType() == RoomType.Wumpus)
					textFields[i][j].setText("W");
				else if(cave[i][j].getRoomType() == RoomType.Slime)
					textFields[i][j].setText("S");
				else if(cave[i][j].getRoomType() == RoomType.Blood)
					textFields[i][j].setText("B");
				else if(cave[i][j].getRoomType() == RoomType.Goop)
					textFields[i][j].setText("G");
				else if(cave[i][j].getRoomType() == RoomType.Pit)
					textFields[i][j].setText("P");
				else if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == false)
					textFields[i][j].setText("O");
				else
					textFields[i][j].setText(" ");
			}
		}
	}
	
	private void initializeLabelPanel() {
		int size = 12;
		Font myFont = new Font("Courier New", 30);
		textFields = new Label[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				textFields[i][j] = new Label();
				textFields[i][j].setFont(myFont);
				textFields[i][j].setMinHeight(15);
				textFields[i][j].setMinWidth(15);
				//textFields[i][j].setTextFill(Color.web("#0076a3"));
				if(cave[i][j].getRoomType() == RoomType.Hunter)
					textFields[i][j].setText("O");
				else if(cave[i][j].isVisited() == false)
					textFields[i][j].setText("X");
				else if(cave[i][j].getRoomType() == RoomType.Wumpus)
					textFields[i][j].setText("W");
				else if(cave[i][j].getRoomType() == RoomType.Slime)
					textFields[i][j].setText("S");
				else if(cave[i][j].getRoomType() == RoomType.Blood)
					textFields[i][j].setText("B");
				else if(cave[i][j].getRoomType() == RoomType.Goop)
					textFields[i][j].setText("G");
				else if(cave[i][j].getRoomType() == RoomType.Pit)
					textFields[i][j].setText("P");
				else if(cave[i][j].getRoomType() == RoomType.Empty && 
						cave[i][j].isVisited() == false)
					textFields[i][j].setText("O");
				else
					textFields[i][j].setText(" ");
				gamePanel.add(textFields[i][j], i, j);
			}
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		wumpusGame = (WumpusEventDrivenGame) o;
		// TODO Auto-generated method stub
		updateLabelPanel();
		gameState.setText(wumpusGame.checkGameState());
		if(wumpusGame.isGameOver())
			showAll();
	}

	private class movementHandler implements EventHandler<KeyEvent>  {

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
			
			if(!wumpusGame.isGameOver()) {
				
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
				
			}
			gameState.setText(wumpusGame.checkGameState());

		}
	}
}
