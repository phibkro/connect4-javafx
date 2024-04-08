package connect4.mvc;

import java.io.IOException;
import java.net.URL;

import connect4.models.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

public class View {
  private Parent root;
  private Scene scene;
  private Game game;

  public View(Game game) {
    this.game = game;
    this.root = new FlowPane();
    this.scene = new Scene(this.root,
        this.game.getBoardWidth() * this.game.getTileSize(),
        this.game.getBoardHeight() * this.game.getTileSize());

    // Create and set scene
    FlowPane main = new FlowPane();
    main.setId("board");
  }

  public View(Game game, URL appLocation) throws IOException {
    try {
      this.root = FXMLLoader.load(appLocation);
    } catch (Exception e) {
      e.printStackTrace();
      throw new IOException();
    }

    this.game = game;
    this.scene = new Scene(this.root);
  }

  public Parent asParent() {
    return this.root;
  }

  public Scene asScene() {
    return this.scene;
  }
}