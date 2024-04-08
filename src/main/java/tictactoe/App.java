package tictactoe;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    String appPath = "App.fxml";
    URL appLocation = getClass().getResource(appPath);
    Scene scene = new Scene(FXMLLoader.load(appLocation));

    primaryStage.setTitle("Tic-Tac-Toe");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}
