/*
 * Written by: Tyler Horvat
 * CSC 335 Summer 2017
 */

package controller;
import java.lang.reflect.InvocationTargetException;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.WumpusEventDrivenGame;
import view.ImageView;
import view.TextView;

public class MainWumpus extends Application {

  public static void main(String[] args) throws InvocationTargetException {
    Application.launch(args);
    
   //GameConsole console = new GameConsole();
   //console.runGame();
    //wumpusGame = new WumpusEventDrivenGame();
   
  }
  
  private static WumpusEventDrivenGame wumpusGame;
  private MenuBar menuBar;
  private Observer currentView;
  private Observer imageView;
  private Observer textView;

  private BorderPane window;

  @Override
  public void start(Stage stage) {
    stage.setTitle("Wumpus");
    window = new BorderPane();
    
    setupMenu();
    startGame();
    window.setTop(menuBar);
    
    imageView = new ImageView(wumpusGame);
    textView = new TextView(wumpusGame);
    //wumpusGame.getMap().addObserver(imageView);
    //wumpusGame.getMap().addObserver(textView);
    wumpusGame.addObserver(imageView);
    wumpusGame.addObserver(textView);
    setViewTo(imageView);
    

    //window.setCenter(canvas);
    Scene scene = new Scene(window, 600, 800);
    
    
    stage.setScene(scene);
    stage.show();
  }
  
  private void startGame() {
	  wumpusGame = new WumpusEventDrivenGame();
	  wumpusGame.startNewGame();
  }
  
  private void setupMenu() {
	  MenuItem newGame = new MenuItem("New Game");
	  MenuItem imageView = new MenuItem("Image View");
	  MenuItem textView = new MenuItem("Text View");
	  Menu view = new Menu("View");
	  view.getItems().addAll(newGame, imageView, textView);
	  
	  menuBar = new MenuBar();
	  menuBar.getMenus().add(view);
	  
	  MenuItemListener menuListener = new MenuItemListener();
	  newGame.setOnAction(menuListener);
	  imageView.setOnAction(menuListener);
	  textView.setOnAction(menuListener);
  }
  
  private void setViewTo(Observer newView) {
	  window.setCenter(null);
	  currentView = newView;
	  window.setCenter((Node) currentView);
  }
  
  private class MenuItemListener implements EventHandler<ActionEvent> {

	    @Override
	    public void handle(ActionEvent e) {
	      // Find out the text of the JMenuItem that was just clicked
	      String text = ((MenuItem) e.getSource()).getText();
	      if (text.equals("Image View"))
	        setViewTo(imageView);
	      else if (text.equals("Text View"))
	        setViewTo(textView);   
	      else if (text.equals("New Game")) {
	    	  wumpusGame.deleteObservers();
	    	  startGame();
	    	  imageView = new ImageView(wumpusGame);
	    	  textView = new TextView(wumpusGame);
	    	  wumpusGame.addObserver(imageView);
	    	  wumpusGame.addObserver(textView);
	    	  
	    	  setViewTo(imageView);
	    	  
	      } 	
	    }
  }
}