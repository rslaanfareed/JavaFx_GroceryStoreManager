package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageInventoryGUI {
    private InventoryManager manager;

    public ManageInventoryGUI(InventoryManager manager) {
        this.manager = manager;
    }

    public void show() {
        Stage stage = new Stage();

        TextField nameField = new TextField();
        nameField.setPromptText("Item Name");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        Button addBtn = new Button("Add / Update Item");
        Button deleteBtn = new Button("Delete Item");

        addBtn.getStyleClass().add("my-button");
        deleteBtn.getStyleClass().add("delete-button");

        addBtn.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int qty = Integer.parseInt(quantityField.getText().trim());

                System.out.println("ADDING ITEM");   // 👈 ADD THIS
                manager.addItem(new Item(name, price, qty));

                System.out.println("Items now: " + manager.getItems().size()); // 👈 ADD THIS

                showAlert("Success", "Item saved.");
            } catch (Exception ex) {
                showAlert("Error", "Invalid input.");
            }
        });


        deleteBtn.setOnAction(e -> {
            manager.deleteItem(nameField.getText().trim());
            showAlert("Deleted", "Item removed.");
        });

        VBox vb = new VBox(10, nameField, priceField, quantityField, addBtn, deleteBtn);
        vb.setPadding(new Insets(20));
        vb.getStyleClass().add("root");

        Scene scene = new Scene(vb, 400, 350);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Manage Inventory");
        stage.show();
    }

    private void showAlert(String title, String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}
