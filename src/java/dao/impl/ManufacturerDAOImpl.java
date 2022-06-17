package dao.impl;

import dao.DBContext;
import dao.ManufacturerDAO;
import entity.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManufacturerDAOImpl extends DBContext implements ManufacturerDAO{

    public Vector<Manufacturer> getManufacturerList() throws Exception{
        // Create vector to store all Categories
        Vector<Manufacturer> manufacturers = new Vector<>();

        // Create value atribute of each Category
        int manufacturerID;
        String manufacturerName;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select * from Manufacturer";

        // Resultset to store all Categories 
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        // Get all categories store to vector
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sqlQuery);
            rs = prepare.executeQuery();
            while (rs.next()) {
                manufacturerID = rs.getInt(1);
                manufacturerName = rs.getString(2);
                manufacturers.add(new Manufacturer(manufacturerID, manufacturerName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return manufacturers;
    }
}
