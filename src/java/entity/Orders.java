package entity;

import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Orders {
    private int ID;
    private int UserID;
    private int TotalPrice;
    private String Note;
    private int Status;
    private String DayBuy;
    private ShipInfo Shipp;
    private OrderStatus orderStatus;
    private ArrayList<OrderDetail> OrderDetail;

//    public OrderStatus getOrderStatus() {
//        return orderStatus;
//    }
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public int getUserID() {
//        return UserID;
//    }
//
//    public void setUserID(int UserID) {
//        this.UserID = UserID;
//    }
//
//    public int getTotalPrice() {
//        return TotalPrice;
//    }
//
//    public void setTotalPrice(int TotalPrice) {
//        this.TotalPrice = TotalPrice;
//    }
//
//    public char getNote() {
//        return Note;
//    }
//
//    public void setNote(char Note) {
//        this.Note = Note;
//    }
//
//    public int getStatus() {
//        return Status;
//    }
//
//    public void setStatus(int Status) {
//        this.Status = Status;
//    }
//
//    public char getDayBuy() {
//        return DayBuy;
//    }
//
//    public void setDayBuy(char DayBuy) {
//        this.DayBuy = DayBuy;
//    }
    public Orders() {
    }

    public Orders(int ID, int UserID, int TotalPrice, String Note, int Status, String DayBuy, ShipInfo Shipp, OrderStatus orderStatus, ArrayList<OrderDetail> OrderDetail) {
        this.ID = ID;
        this.UserID = UserID;
        this.TotalPrice = TotalPrice;
        this.Note = Note;
        this.Status = Status;
        this.DayBuy = DayBuy;
        this.Shipp = Shipp;
        this.orderStatus = orderStatus;
        this.OrderDetail = OrderDetail;
    }

    
    public Orders(int ID, int UserID, int TotalPrice, String Note, int Status, String DayBuy, ArrayList<OrderDetail> OrderDetail) {
        this.ID = ID;
        this.UserID = UserID;
        this.TotalPrice = TotalPrice;
        this.Note = Note;
        this.Status = Status;
        this.DayBuy = DayBuy;
        this.OrderDetail = OrderDetail;
    }
    
    public Orders(int ID, int UserID, int TotalPrice, String Note, int Status, String DayBuy, ShipInfo Shipp, OrderStatus orderStatus) {
        this.ID = ID;
        this.UserID = UserID;
        this.TotalPrice = TotalPrice;
        this.Note = Note;
        this.Status = Status;
        this.DayBuy = DayBuy;
        this.Shipp = Shipp;
        this.orderStatus = orderStatus;
    }


    public Orders(int ID, int UserID, int TotalPrice, String Note, int Status, String DayBuy) {
        this.ID = ID;
        this.UserID = UserID;
        this.TotalPrice = TotalPrice;
        this.Note = Note;
        this.Status = Status;
        this.DayBuy = DayBuy;
        this.Shipp = Shipp;
        this.orderStatus = orderStatus;
    }

    public Orders(int UserID, int TotalPrice, String Note, int Status, String DayBuy) {
        this.UserID = UserID;
        this.TotalPrice = TotalPrice;
        this.Note = Note;
        this.Status = Status;
        this.DayBuy = DayBuy;
    }
}
