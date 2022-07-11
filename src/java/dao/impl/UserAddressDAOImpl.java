/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DBContext;
import dao.UserAddressDAO;
import entity.UserAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author viet
 */
public class UserAddressDAOImpl extends DBContext implements UserAddressDAO {

    public UserAddress getAll(String id) { //Get user data in the database
        String sql = "select * from UserAddress UA full outer join Users U on UA.UserID = U.UserID where U.UserID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        ResultSet rs = null;
        UserAddress UA = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, id);
            rs = prepare.executeQuery();
            while (rs.next()) {
                UA = new UserAddress();
                UA.setImage(rs.getString(15));
                UA.setEmail(rs.getString(10));
                UA.setID(rs.getInt(1));
                UA.setPhoneNum(rs.getString(6));
                UA.setShipAddress(rs.getString(4));
                UA.setShipCityID(rs.getInt(5));
                UA.setShipName(rs.getString(3));
                UA.setUserID(rs.getInt(2));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return UA;
    }

    public void edit(String image, String name, String email, String phone, String address, String id) {
        String sql = "update Users set email = ?,Image=? where UserID = ?";
        String query = "update UserAddress set ShipName = ?,ShipAddress=?,PhoneNum=? where UserID = ?";
        Connection conn = null;
        PreparedStatement prepare = null;
        PreparedStatement prepare1 = null;
        try {
            conn = getConnection();
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, email);
            prepare.setString(2, image);
            prepare.setString(3, id);
            prepare.executeUpdate();
            prepare1 = conn.prepareStatement(query);
            prepare1.setString(1, name);
            prepare1.setString(2, address);
            prepare1.setString(3, phone);
            prepare1.setString(4, id);
            prepare1.executeUpdate();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static void main(String[] args) {
        UserAddressDAOImpl dao = new UserAddressDAOImpl();
//        dao.edit("lg.png","Nguyen Viet","nviet0139@gmail.com","0349175696","Ha Noi","10");
        UserAddress us = dao.getAll("10");
        System.out.println(us);
    }

    @Override
    public ArrayList<UserAddress> getUserAddressList() throws Exception {
        String query = "SELECT * FROM UserAddress";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<UserAddress> arrUserAddress = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                arrUserAddress.add(new UserAddress(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("ShipName"),
                        rs.getString("ShipAddress"),
                        rs.getInt("ShipCity"),
                        rs.getString("PhoneNum")
                ));
            }
            return arrUserAddress;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public ArrayList<UserAddress> getUserAddressListByUserID(int userID) throws Exception {
        String query = "SELECT * FROM UserAddress WHERE UserID = " + userID + " Order By ID DESC";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<UserAddress> arrUserAddress = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                arrUserAddress.add(new UserAddress(
                        rs.getInt("ID"),
                        rs.getInt("UserID"),
                        rs.getString("ShipName"),
                        rs.getString("ShipAddress"),
                        rs.getInt("ShipCityID"),
                        rs.getString("PhoneNum")
                ));
            }
            return arrUserAddress;
        } catch (Exception e) {
            throw e;
        } finally {
            closeRS(rs);
            closePrepareStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public int addUserAddress(UserAddress userAddress) throws Exception {
        int n = 0;
        String preSql = "INSERT INTO [UserAddress]\n"
                + "           ([UserID]\n"
                + "           ,[ShipName]\n"
                + "           ,[ShipAddress]\n"
                + "           ,[ShipCityID]\n"
                + "           ,[PhoneNum])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement prepare = null;
        try {

            conn = getConnection();
            prepare = conn.prepareStatement(preSql);

            prepare.setInt(1, userAddress.getUserID());
            prepare.setString(2, userAddress.getShipName());
            prepare.setString(3, userAddress.getShipAddress());
            prepare.setInt(4, userAddress.getShipCityID());
            prepare.setString(5, userAddress.getPhoneNum());

            n = prepare.executeUpdate();

//            n = pre.executeUpdate();
        } finally {
            closePrepareStatement(prepare);
            closeConnection(conn);
        }

        return n;
    }

    @Override
    public int deleteUserAddress(int userAddressID) throws Exception {
        int n = 0;
        String sql = "DELETE FROM [ElectronicShop].[dbo].[UserAddress]\n"
                + "      WHERE ID = " + userAddressID;

        try {
            Statement state = getConnection().createStatement();

            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    @Override
    public int editUserAddress(UserAddress userAddress) throws Exception{
        int n = 0;
        String preSql = "UPDATE [ElectronicShop].[dbo].[UserAddress]\n"
                + "   SET [UserID] = ?\n"
                + "      ,[ShipName] = ?\n"
                + "      ,[ShipAddress] = ?\n"
                + "      ,[ShipCityID] = ?\n"
                + "      ,[PhoneNum] = ?\n"
                + " WHERE ID = ?";

        Connection conn = null;
        PreparedStatement pre = null;

        try {
            conn = getConnection();
            pre = conn.prepareStatement(preSql);

            pre.setInt(1, userAddress.getUserID());
            pre.setString(2, userAddress.getShipName());
            pre.setString(3, userAddress.getShipAddress());
            pre.setInt(4, userAddress.getShipCityID());
            pre.setString(5, userAddress.getPhoneNum());
            pre.setInt(6, userAddress.getID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closePrepareStatement(pre);
            closeConnection(conn);
        }

        return n;
    }
}
