/*package com.example.projet_javafx.GUI;

import com.example.projet_javafx.Entities.Menuitems;
import com.example.projet_javafx.Services.ServicePlat;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PlatUi extends Application {
    private ServicePlat servicePlat = new ServicePlat();
    private TextArea resultTextArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Plat Management");

        // Create UI components
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setStyle("-fx-background-color: #c4daf3; ");

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10, 10, 10, 10));

        Button addButton = createStyledButton("Add Plat", "button1");
        Button editButton = createStyledButton("Edit Plat", "button2");
        Button deleteButton = createStyledButton("Delete Plat", "button3");
        Button displayButton = createStyledButton("Display Plat", "button4");
        addButton.setStyle("-fx-background-color: linear-gradient(to top left, #47a2e8, #D4CEE3);");
        editButton.setStyle("-fx-background-color: linear-gradient(to top left, #47A2E8FF, #D4CEE3);");
        deleteButton.setStyle("-fx-background-color: linear-gradient(to top left, #47A2E8FF, #D4CEE3);");
        displayButton.setStyle("-fx-background-color: linear-gradient(to top left, #47A2E8FF, #D4CEE3);");
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, displayButton);

        resultTextArea = new TextArea();

        // Add components to borderPane
        borderPane.setTop(buttonBox);
        borderPane.setCenter(resultTextArea);

        // Add event handlers for the buttons
        addButton.setOnAction(event -> addPlat());
        editButton.setOnAction(event -> editPlat());
        deleteButton.setOnAction(event -> deletePlat());
        displayButton.setOnAction(event -> displayPlats());

        // Set up the scene
        Scene scene = new Scene(borderPane, 600, 400);
        // Set the background color of the BorderPane
        borderPane.setStyle("-fx-background-color: #b6d8f5;");
// Set the background color of the Scene (optional, depends on your preference)
        scene.setFill(javafx.scene.paint.Color.web("#d48ed7"));

        scene.getStylesheets().add("path/to/your/stylesheet.css"); // Replace with your actual stylesheet path
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        return button;
    }

    private void addPlat() {
        // Implement logic to add a review
        try {
            int platId = getUserInput("plat ID:");
            int restaurantID = getUserInput("Restaurant ID:");
            String nom = getStringInput("nom du plat:");
            String commentaire = getStringInput("description:");
            Timestamp dateEvaluation = new Timestamp(System.currentTimeMillis());

            Menuitems plat1 = new Menuitems(platId,restaurantID,nom,commentaire);
            servicePlat.ajouter(plat1);

            // Refresh the displayed reviews
            displayPlats();
        } catch (NumberFormatException | SQLException e) {
            displayError("Error: " + e.getMessage());
        }
    }

    private void editPlat() {
        // Implement logic to edit a review
        try {
            int platIDd = getUserInput("Plat ID to edit:");
            Menuitems existingPlat = servicePlat.get(platIDd);

            if (existingPlat != null) {
                int platID = getUserInput("plat ID (current: " + existingPlat.getMenuItemID() + "):");
                int restaurantID = getUserInput("Restaurant ID (current: " + existingPlat.getRestaurantID() + "):");
                String nomItem = getStringInput("Nom du plat (current: " + existingPlat.getNomItem() + "):");
                String descriptionItem = getStringInput("Description (current: " + existingPlat.getDescription() + "):");

                Menuitems updatedPlat = new Menuitems(platID, restaurantID, nomItem, descriptionItem);
                servicePlat.update(updatedPlat);

                // Refresh the displayed reviews
                displayPlats();
            } else {
                displayError("Review not found for ID: " + platIDd);
            }
        } catch (NumberFormatException | SQLException e) {
            displayError("Error: " + e.getMessage());
        }
    }

    private void deletePlat() {
        // Implement logic to delete a review
        try {
            int reviewID = getUserInput("plat ID to delete:");
            servicePlat.delete(reviewID);

            // Refresh the displayed reviews
            displayPlats();
        } catch (NumberFormatException | SQLException e) {
            displayError("Error: " + e.getMessage());
        }
    }

    private void displayPlats() {
        // Implement logic to display all reviews
        try {
            ArrayList<Menuitems> menuitems = servicePlat.readAll();
            resultTextArea.clear();
            for (Menuitems plat : menuitems) {
                resultTextArea.appendText(plat.toString() + "\n");
            }
        } catch (SQLException e) {
            displayError("Error: " + e.getMessage());
        }
    }

    private int getUserInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(prompt);
        dialog.showAndWait();
        return Integer.parseInt(dialog.getResult());
    }

    private double getDoubleInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(prompt);
        dialog.showAndWait();
        return Double.parseDouble(dialog.getResult());
    }

    private String getStringInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(prompt);
        dialog.showAndWait();
        return dialog.getResult();
    }

    private void displayError(String message) {
        resultTextArea.setText(message);
    }
}
*/