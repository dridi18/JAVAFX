package com.example.projet_javafx.Entities;

public class Menuitems {
    int MenuItemID ;
    String NomItem;
    String description ;
    int restaurantID;

    public Menuitems(int menuItemID, String nomItem) {
        MenuItemID = menuItemID;
        NomItem = nomItem;

    }
    public Menuitems(int restaurantId, String nomItem, String description){
        this.restaurantID = restaurantId ;
        this.NomItem = nomItem;
        this.description = description;
    }
   public Menuitems(){}
    public Menuitems(int id, int RestaurantId , String nom, String description){
        this.MenuItemID =  id ;
        this.NomItem = nom;
        this.description = description;
        this.restaurantID = RestaurantId;

    }


    public int getRestaurantID(){
        return 1;
    }
    public String getDescription(){
        return this.description;
    }

    public int getMenuItemID() {
        return MenuItemID;
    }

    public void setMenuItemID(int menuItemID) {
        MenuItemID = menuItemID;
    }

    public String getNomItem() {
        return NomItem;
    }

    public void setNomItem(String nomItem) {
        NomItem = nomItem;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setDescription(String description){
        this.description = description;
    }
    @Override
    public String toString() {
        return "Menuitems{" +
                "menuItemID=" + MenuItemID +
                ", restaurantID=" + restaurantID +
                ", nomItem='" + NomItem + '\'' +
                ", description='" + description + '\'' +
                // Add other fields if needed
                '}';
    }
}
