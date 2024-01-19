package com.example.projet_javafx.Controllers;

import com.example.projet_javafx.Entities.Menuitems;
import com.example.projet_javafx.MainApplication;
import com.example.projet_javafx.Services.ServicePlat;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class MenuItemController {

    @FXML
    private TableView<Menuitems> menuItemTableView;

    @FXML
    private TextField MenuItemIdTextField;

    @FXML
    private TextField restaurantIdTextField;

    @FXML
    private TextField nomItemTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TableColumn<Menuitems, Integer> menuItemIdColumn;

    @FXML
    private TableColumn<Menuitems, Integer> restaurantIdColumn;

    @FXML
    private TableColumn<Menuitems, String> nomItemColumn;

    @FXML
    private TableColumn<Menuitems, String> descriptionColumn;

    @FXML
    private TableColumn<Menuitems, Void> operationsColumn;

    private final ServicePlat servicePlat = new ServicePlat();

    @FXML
    public void initialize() {
        ObservableList<Menuitems> menuItemList = FXCollections.observableArrayList();

        try {
            menuItemList.addAll(servicePlat.readAll());
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        menuItemTableView.setRowFactory(param -> new MenuItemTableRow());

        menuItemTableView.setItems(menuItemList);

        menuItemIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMenuItemID()));
        restaurantIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getRestaurantID()));
        nomItemColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomItem()));
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        operationsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("❌");

            {
                deleteButton.getStyleClass().add("delete-button");
                deleteButton.setOnAction(event -> {
                    Menuitems menuItem = getTableView().getItems().get(getIndex());
                    handleDeleteMenuItem(menuItem);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        menuItemTableView.getSelectionModel()
                .selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        handleMenuItemSelection(newSelection);
                    }
                });
    }

    private class MenuItemTableRow extends javafx.scene.control.TableRow<Menuitems> {

        @Override
        protected void updateItem(Menuitems menuItem, boolean empty) {
            super.updateItem(menuItem, empty);

            if (empty || menuItem == null) {
                setText(null);
                setGraphic(null);
            } else {
                // Handle displaying row data if necessary
            }
        }
    }

    private void handleDeleteMenuItem(Menuitems menuItem) {
        try {
            servicePlat.delete(menuItem.getMenuItemID());
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleMenuItemSelection(Menuitems menuItem) {
        restaurantIdTextField.setText(String.valueOf(menuItem.getRestaurantID()));
        nomItemTextField.setText(menuItem.getNomItem());
        descriptionTextField.setText(menuItem.getDescription());
    }

    @FXML
    private void handleAddMenuItem() {
        int MenuItemId = Integer.parseInt(MenuItemIdTextField.getText());
        int restaurantId = Integer.parseInt(restaurantIdTextField.getText());
        String nomItem = nomItemTextField.getText();
        String description = descriptionTextField.getText();

        Menuitems newMenuItem = new Menuitems(MenuItemId,restaurantId, nomItem, description);

        try {
            servicePlat.ajouter(newMenuItem);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MenuItemIdTextField.clear();

        restaurantIdTextField.clear();
        nomItemTextField.clear();
        descriptionTextField.clear();
    }

    @FXML
    private void handleUpdateMenuItem() {
        int MenuItemId = Integer.parseInt(MenuItemIdTextField.getText());
        int restaurantId = Integer.parseInt(restaurantIdTextField.getText());
        String nomItem = nomItemTextField.getText();
        String description = descriptionTextField.getText();
        System.out.println("updaaaaaaaaaaaaaaaaaaaaaaaaaaaatttttttttttttteeeeeeeeee: "+MenuItemId+restaurantId+nomItem+description);

        Menuitems updatedMenuItem = new Menuitems(MenuItemId,restaurantId, nomItem, description);

        try {
        //    Menuitems selectedMenuItem = menuItemTableView.getSelectionModel().getSelectedItem();
        //    System.out.println("selected menuItem "+selectedMenuItem);
        //    if (selectedMenuItem != null) {
              //  updatedMenuItem.setMenuItemID(selectedMenuItem.getMenuItemID());
                servicePlat.update(updatedMenuItem);
                initialize();
          //  }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MenuItemIdTextField.clear();
        restaurantIdTextField.clear();
        nomItemTextField.clear();
        descriptionTextField.clear();
    }



    @FXML
    private void handleManageRestaurantsButtonAction() throws IOException {
        MainApplication.showManagePlatsPage();
    }

    @FXML
    private void handleEvaluateRestaurantsButtonAction() throws IOException {
        MainApplication.showManageReservationsPage();
    }

    @FXML
    private void handleManageReservationsButtonAction() throws IOException {
        MainApplication.showManageReservationsPage();
    }
}
