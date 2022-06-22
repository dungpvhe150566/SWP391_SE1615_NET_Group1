package entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Builder
@Getter
@Setter
@ToString
public class OrderDetail {

    private int ID;

    private int OrderID;

    private int ProductID;

    private String ProductName;

    private int ProductPrice;

    private int Quantity;

//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public int getOrderID() {
//        return OrderID;
//    }
//
//    public void setOrderID(int OrderID) {
//        this.OrderID = OrderID;
//    }
//
//    public int getProductID() {
//        return ProductID;
//    }
//
//    public void setProductID(int ProductID) {
//        this.ProductID = ProductID;
//    }
//
//    public int getProductName() {
//        return ProductName;
//    }
//
//    public void setProductName(int ProductName) {
//        this.ProductName = ProductName;
//    }
//
//    public int getProductPrice() {
//        return ProductPrice;
//    }
//
//    public void setProductPrice(int ProductPrice) {
//        this.ProductPrice = ProductPrice;
//    }
//
//    public int getQuantity() {
//        return Quantity;
//    }
//
//    public void setQuantity(int Quantity) {
//        this.Quantity = Quantity;
//    }

    public OrderDetail() {
    }

    public OrderDetail(int ID, int OrderID, int ProductID, String ProductName, int ProductPrice, int Quantity) {
        this.ID = ID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.Quantity = Quantity;
    }

}
