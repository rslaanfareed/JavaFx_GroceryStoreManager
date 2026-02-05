package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculateBillGUI {
    private InventoryManager manager;

    public CalculateBillGUI(InventoryManager manager) {
        this.manager = manager;
    }

    public void show() {
        Stage stage = new Stage();

        ComboBox<String> itemComboBox = new ComboBox<>();
        itemComboBox.getStyleClass().add("combo-box");
        for (Item item : manager.getItems()) {
            itemComboBox.getItems().add(item.getName());
        }

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        Button addButton = new Button("Add to Bill");
        addButton.getStyleClass().add("my-button");

        Label totalLabel = new Label("Total: 0");
        totalLabel.getStyleClass().add("label-title");

        ListView<String> billList = new ListView<>();

        double[] total = {0};

        addButton.setOnAction(e -> {
            try {
                int qty = Integer.parseInt(quantityField.getText());
                if (qty <= 0) throw new Exception();

                for (Item item : manager.getItems()) {
                    if (item.getName().equalsIgnoreCase(itemComboBox.getValue())) {
                        if (item.getQuantity() >= qty) {
                            double cost = item.getPrice() * qty;
                            total[0] += cost;
                            totalLabel.setText("Total: " + total[0]);
                            billList.getItems().add(item.getName() + " x" + qty + " = " + cost);
                            item.setQuantity(item.getQuantity() - qty);
                            manager.saveInventory();
                        }
                    }
                }
            } catch (Exception ex) {
                showAlert("Error", "Invalid quantity.");
            }
        });

        VBox vb = new VBox(10, itemComboBox, quantityField, addButton, billList, totalLabel);
        vb.getStyleClass().add("root");

        Scene scene = new Scene(vb, 450, 400);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        stage.setTitle("Calculate Bill");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
