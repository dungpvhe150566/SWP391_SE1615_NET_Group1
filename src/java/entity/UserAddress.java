package entity;

public class UserAddress {

    private int ID;

    private int UserID;

    private String ShipName;

    private String ShipAddress;

    private int ShipCityID;

    private String PhoneNum;
    
    private String Image;
    
    private String email;

    public UserAddress() {
    }

    public UserAddress(int UserID, String ShipName, String ShipAddress, int ShipCityID, String PhoneNum) {
        this.UserID = UserID;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCityID = ShipCityID;
        this.PhoneNum = PhoneNum;
    }
    
    

    public UserAddress(int ID, int UserID, String ShipName, String ShipAddress, int ShipCityID, String PhoneNum, String Image, String email) {
        this.ID = ID;
        this.UserID = UserID;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCityID = ShipCityID;
        this.PhoneNum = PhoneNum;
        this.Image = Image;
        this.email = email;
    }

    public UserAddress(int ID, int UserID, String ShipName, String ShipAddress, int ShipCityID, String PhoneNum) {
        this.ID = ID;
        this.UserID = UserID;
        this.ShipName = ShipName;
        this.ShipAddress = ShipAddress;
        this.ShipCityID = ShipCityID;
        this.PhoneNum = PhoneNum;
    }

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String ShipName) {
        this.ShipName = ShipName;
    }

    public String getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    public int getShipCityID() {
        return ShipCityID;
    }

    public void setShipCityID(int ShipCityID) {
        this.ShipCityID = ShipCityID;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    @Override
    public String toString() {
        return "UserAddress{" + "ID=" + ID + ", UserID=" + UserID + ", ShipName=" + ShipName + ", ShipAddress=" + ShipAddress + ", ShipCityID=" + ShipCityID + ", PhoneNum=" + PhoneNum + ", Image=" + Image + ", email=" + email + '}';
    }

    
}
