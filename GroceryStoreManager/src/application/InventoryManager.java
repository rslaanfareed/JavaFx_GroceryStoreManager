package application;

import java.io.*;
import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Item> items = new ArrayList<>();

    public InventoryManager() {
        loadInventory();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item newItem) {
        boolean found = false;

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(newItem.getName())) {
                item.setPrice(newItem.getPrice());
                item.setQuantity(item.getQuantity() + newItem.getQuantity()); // ADD stock
                found = true;
                break;
            }
        }

        if (!found) {
            items.add(newItem);
        }

        saveInventory();
    }

    public void deleteItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
        saveInventory();
    }

    public void saveInventory() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("inventory.txt"));

            for (Item item : items) {
                writer.println(item.getName());
                writer.println(item.getPrice());
                writer.println(item.getQuantity());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory() {
        try {
            File file = new File("inventory.txt");
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String name;

            while ((name = reader.readLine()) != null) {
                double price = Double.parseDouble(reader.readLine());
                int quantity = Integer.parseInt(reader.readLine());
                items.add(new Item(name, price, quantity));
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading inventory.");
        }
    }

}
