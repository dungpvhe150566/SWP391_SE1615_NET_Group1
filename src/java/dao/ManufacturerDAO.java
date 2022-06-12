package dao;

import entity.Manufacturer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManufacturerDAO extends DBContext {

    public Vector<Manufacturer> getManufacturerList() {
        // Create vector to store all Categories
        Vector<Manufacturer> manufacturers = new Vector<>();

        // Create value atribute of each Category
        int manufacturerID;
        String manufacturerName;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Manufacturer";

        // Resultset to store all Categories 
        ResultSet rs = getData(sqlQuery);

        // Get all categories store to vector
        try {
            while (rs.next()) {
                manufacturerID = rs.getInt(1);
                manufacturerName = rs.getString(2);
                manufacturers.add(new Manufacturer(manufacturerID, manufacturerName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return manufacturers;
    }
}
