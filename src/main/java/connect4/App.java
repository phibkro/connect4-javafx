package connect4;

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
  public void start(Stage primaryStage) throws Exception {
    String appPath = "App.fxml";
    URL appLocation = getClass().getResource(appPath);
    primaryStage.setScene(new Scene(FXMLLoader.load(appLocation)));

    primaryStage.show();
  }
}
