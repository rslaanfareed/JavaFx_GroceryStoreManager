package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowInventoryGUI {

    private InventoryManager manager;

    public ShowInventoryGUI(InventoryManager manager) {
        this.manager = manager;
    }

    public void show() {
        Stage stage = new Stage();

        ListView<String> list = new ListView<>();

        // 🔥 DIRECTLY LOAD ITEMS
        for (Item item : manager.getItems()) {
            list.getItems().add(
                item.getName() + " | Price: " + item.getPrice() + " | Qty: " + item.getQuantity()
            );
        }

        Button refresh = new Button("Refresh");

        refresh.setOnAction(e -> {
            list.getItems().clear();
            for (Item item : manager.getItems()) {
                list.getItems().add(
                    item.getName() + " | Price: " + item.getPrice() + " | Qty: " + item.getQuantity()
                );
            }
        });

        VBox vb = new VBox(10, list, refresh);
        vb.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(vb, 400, 400));
        stage.setTitle("Inventory");
        stage.show();
    }
}
