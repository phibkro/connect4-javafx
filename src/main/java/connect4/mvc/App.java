package connect4.mvc;

import java.io.IOException;
import java.net.URL;

import connect4.models.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
  private Game game;
  private View view;
  private Controller controller;
  private boolean useFXML = false;

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    this.game = new Game();
    this.controller = new Controller(this.game);
    if (useFXML) {
      String appPath = "App.fxml";
      URL appLocation = getClass().getResource(appPath);
      this.view = new View(this.game, appLocation);
    } else {
      this.view = new View(this.game);
    }

    primaryStage.setScene(this.view.asScene());
    primaryStage.show();

    // Configure settings
    primaryStage.setTitle("Connect-4");
    primaryStage.setResizable(false);
  }
}
