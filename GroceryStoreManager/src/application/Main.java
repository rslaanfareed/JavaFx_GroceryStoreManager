package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    InventoryManager manager = new InventoryManager();

    @Override
    public void start(Stage primaryStage) {

        Label welcome = new Label("WELCOME TO GROCERY STORE MANAGER");
        welcome.getStyleClass().add("welcome-label");

        Button manageBtn = new Button("Manage Inventory");
        Button showBtn = new Button("Show Inventory");
        Button billBtn = new Button("Calculate Bill");

        manageBtn.getStyleClass().add("my-button");
        showBtn.getStyleClass().add("my-button");
        billBtn.getStyleClass().add("my-button");

        manageBtn.setOnAction(e -> new ManageInventoryGUI(manager).show());
        showBtn.setOnAction(e -> new ShowInventoryGUI(manager).show());
        billBtn.setOnAction(e -> new CalculateBillGUI(manager).show());

        VBox vb = new VBox(20, welcome, manageBtn, showBtn, billBtn);
        vb.setAlignment(Pos.CENTER);
        vb.getStyleClass().add("root");

        Scene scene = new Scene(vb, 500, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setTitle("Grocery Store Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
