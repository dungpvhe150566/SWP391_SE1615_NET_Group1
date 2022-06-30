package dao;

import entity.UserAddress;
import java.util.ArrayList;

public interface UserAddressDAO {
    public ArrayList<UserAddress> getUserAddressList() throws Exception;
    public ArrayList<UserAddress> getUserAddressListByUserID(int userID) throws Exception;
}
