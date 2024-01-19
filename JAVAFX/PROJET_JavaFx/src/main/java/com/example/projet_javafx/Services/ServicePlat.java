package com.example.projet_javafx.Services;

import com.example.projet_javafx.Entities.Menuitems;
import com.example.projet_javafx.Utils.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServicePlat implements IServicePlat<Menuitems> {

    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;

    public ServicePlat() {
        try {
            ste = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouter(Menuitems menuItem) throws SQLException {
        String req = "INSERT INTO menuitems (MenuItemId, RestaurantId, NomItem, Description) VALUES (" +
                menuItem.getMenuItemID() + ", " +
                menuItem.getRestaurantID() + ", '" +
                menuItem.getNomItem() + "', '" +
                menuItem.getDescription() + "')";

        ste.executeUpdate(req);
    }



    @Override
    public void update(Menuitems menuItem) throws SQLException {
        String req = "UPDATE menuitems SET " +
                "RestaurantID = '" + menuItem.getRestaurantID() + "', " +

                "NomItem = '" + menuItem.getNomItem() + "', " +
                "Description = '" + menuItem.getDescription() + "' " +
                "WHERE MenuItemId = " + menuItem.getMenuItemID();

        ste.executeUpdate(req);
    }
    //
    @Override
    public void delete(int menuItemId) throws SQLException {
        String req = "DELETE FROM menuitems WHERE MenuItemId = " + menuItemId;
        ste.executeUpdate(req);
    }
  


    @Override
    public ArrayList<Menuitems> readAll() throws SQLException {
        ArrayList<Menuitems> list = new ArrayList<>();
        try (ResultSet resultSet = ste.executeQuery("SELECT * FROM MenuItems")) {
            while (resultSet.next()) {
                int menuItemID = resultSet.getInt("MenuItemID");
                int restaurantID = resultSet.getInt("RestaurantID");
                String nomItem = resultSet.getString("NomItem");
                String description = resultSet.getString("Description");
         //       double prix = resultSet.getDouble("Prix");
                Menuitems menuItem = new Menuitems(menuItemID,1, nomItem, description);
                list.add(menuItem);
            }
        }
        return list;
    }

    @Override
    public Menuitems get(int menuItemId) throws SQLException {
        String req = "SELECT * FROM menuitems WHERE MenuItemId = " + menuItemId;
        ResultSet rs = ste.executeQuery(req);

        if (rs.next()) {
            Menuitems menuItem = new Menuitems();
            menuItem.setMenuItemID(rs.getInt("MenuItemId"));
            menuItem.setRestaurantID(rs.getInt("RestaurantId"));
            menuItem.setNomItem(rs.getString("NomItem"));
            menuItem.setDescription(rs.getString("Description"));
            return menuItem;
        }

        return null; // No menu item found with the given ID
    }

}
