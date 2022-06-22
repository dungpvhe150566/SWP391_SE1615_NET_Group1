package dao.impl;

import dao.DBContext;
import dao.ManufacturerDAO;
import entity.Manufacturer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class ManufacturerDAOImpl extends DBContext implements ManufacturerDAO {

    public ArrayList<Manufacturer> getManufacturerList() throws Exception {
        // Create vector to store all Categories
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        // Create value atribute of each Category
        int manufacturerID,count;
        String manufacturerName;

        // Query Statement to get all Categories in Database 
        String sqlQuery = "select m.ManufacturerID,m.ManufacturerName,count(p.ProductID) as [count]\n"
                + "from Manufacturer m join Product p on p.ManufacturerID = m.ManufacturerID\n"
                + "group by m.ManufacturerID,m.ManufacturerName";

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
                count = rs.getInt(3);
                manufacturers.add(new Manufacturer(manufacturerID, manufacturerName, count));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeRS(rs);
            closePrepareStatement(prepare);
            closeConnection(conn);
        }
        return manufacturers;
    }
}
