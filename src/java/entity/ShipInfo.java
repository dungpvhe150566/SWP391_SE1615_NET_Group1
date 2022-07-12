package entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ShipInfo {

    private int ID;

    private int Order_ID;

    private String CustomerName;

    private String ShippingAddress;

    private int ShipCityID;

    private String PhoneNum;

    private String Note;
    
    

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public int getOrder_ID() {
//        return Order_ID;
//    }
//
//    public void setOrder_ID(int Order_ID) {
//        this.Order_ID = Order_ID;
//    }
//
//    public int getCustomerName() {
//        return CustomerName;
//    }
//
//    public void setCustomerName(int CustomerName) {
//        this.CustomerName = CustomerName;
//    }
//
//    public char getShoppingAddress() {
//        return ShoppingAddress;
//    }
//
//    public void setShoppingAddress(char ShoppingAddress) {
//        this.ShoppingAddress = ShoppingAddress;
//    }
//
//    public int getShipCityID() {
//        return ShipCityID;
//    }
//
//    public void setShipCityID(int ShipCityID) {
//        this.ShipCityID = ShipCityID;
//    }
//
//    public char getPhoneNum() {
//        return PhoneNum;
//    }
//
//    public void setPhoneNum(char PhoneNum) {
//        this.PhoneNum = PhoneNum;
//    }
//
//    public char getNote() {
//        return Note;
//    }
//
//    public void setNote(char Note) {
//        this.Note = Note;
//    }

    public ShipInfo(int ID, int Order_ID, String CustomerName, String ShippingAddress, int ShipCityID, String PhoneNum, String Note) {
        this.ID = ID;
        this.Order_ID = Order_ID;
        this.CustomerName = CustomerName;
        this.ShippingAddress = ShippingAddress;
        this.ShipCityID = ShipCityID;
        this.PhoneNum = PhoneNum;
        this.Note = Note;
    }

}
