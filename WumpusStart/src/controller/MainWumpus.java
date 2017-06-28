package controller;
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

public class MainWumpus extends Application {

  public static void main(String[] args) {
    Application.launch(args);
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