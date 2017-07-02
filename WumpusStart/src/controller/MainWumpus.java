package controller;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * This is a bare minimum start to the event drive GUI.
 * 
 * I also added a Canvas and drew all images just to see if the
 * images are present and they get drawn over a black fillRect.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Map;
import model.Player;
import model.RoomType;
import model.Wumpus;

public class MainWumpus extends Application {

  public static void main(String[] args) throws InvocationTargetException {
    //Application.launch(args);
    
    boolean gameOver = false;
    Map map = new Map();
    Player player = map.getPlayer();
    Scanner scanner = new Scanner(System.in);
    boolean hitWumpus = false;
    int checkNextMove = 0;
    boolean wumpusNearby = false;
    
    do {
    	
    	System.out.println(map.toString());
        System.out.print("Move (n, e, s, w, arrow)? ");
        String playerMove = scanner.next();
        
        Move move = new Move(playerMove, player.getPlayerLocation());
        Point moveTo = move.makeMove();
        System.out.println();
        
        if(moveTo == null) {
        	Scanner arrowScan = new Scanner(System.in);
			System.out.print("Shoot (n, e, s, w)? ");
			String arrowDirection = arrowScan.next();
			hitWumpus = player.shootArrow(arrowDirection);
			arrowScan.close();
			gameOver = true;
        }
        else {
        	player.setPlayerLocation(moveTo);
        }
        
        if(wumpusNearby) {
        	System.out.println();
        	System.out.println("I smell something foul, from 1 or 2 rooms away");
        	System.out.println();	
        }
        	
        Point wumpus = Wumpus.getWumpusLocation();
        if(player.getPreviousRoomType() == RoomType.Pit)
        {
        	checkNextMove = -1;
        	gameOver = true;
        }
        if((player.getPlayerLocation().getX() == wumpus.getX()) &&
		    	(player.getPlayerLocation().getY() == wumpus.getY())) {
		    	checkNextMove = -2;
		    	gameOver = true;
		    }
 
        System.out.println();
        
        
    } while (!gameOver);
    
    System.out.println(map.toString());
    
    if(checkNextMove == -1)
    	System.out.println("You fell in a pit. Game Over");
    else if(checkNextMove == -1) 
    	System.out.println("You were eaten by the Wumpus. Game Over");
    else if(hitWumpus)
    	System.out.println("Your arrow hit the Wumpus. Good shooting. Game over.");
    else
    	System.out.println("Your arrow hit you. Bad shooting. Game over.");
    
    
    scanner.close();
    
  }

  private BorderPane window;

  @Override
  public void start(Stage stage) {
    stage.setTitle("Wumpus");
    window = new BorderPane();
    Canvas canvas = new Canvas(600, 740);
    // use false to ensure the images are created before drawImage
    Image blood = new Image("file:images/Blood.png", false);
    Image goop = new Image("file:images/Goop.png", false);
    Image ground = new Image("file:images/Ground.png", false);
    Image slime = new Image("file:images/Slime.png", false);
    Image slimepit = new Image("file:images/Slimepit.png", false);
    Image hunter = new Image("file:images/TheHunter.png", false);
    Image wumpus = new Image("file:images/wumpus.png", false);
   
    GraphicsContext gc = canvas.getGraphicsContext2D();
    // Set a black background
    gc.setFill(Color.BLACK);
    gc.fillRect(0, 0, 600, 740);

    // Draw each image. All are 50 pixels high and wide
    gc.drawImage(blood, 0, 0);
    gc.drawImage(goop, 50, 50);
    gc.drawImage(ground, 100, 100);
    gc.drawImage(slime, 150, 150);
    gc.drawImage(slimepit, 200, 200);
    gc.drawImage(hunter, 250, 250);
    gc.drawImage(wumpus, 300, 300);

    window.setCenter(canvas);
    Scene scene = new Scene(window, 600, 740);
    stage.setScene(scene);
    stage.show();
  }
}