package dao;

import entity.UserAddress;
import java.util.ArrayList;

public interface UserAddressDAO {
    public ArrayList<UserAddress> getUserAddressList() throws Exception;
    public ArrayList<UserAddress> getUserAddressListByUserID(int userID) throws Exception;
    public int addUserAddress(UserAddress userAddress) throws Exception;
    public int deleteUserAddress(int userAddressID) throws Exception;
    public int editUserAddress(UserAddress userAddress) throws Exception;
}
