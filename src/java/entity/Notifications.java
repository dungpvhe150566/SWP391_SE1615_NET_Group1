package entity;

public class Notifications {

    private int ID;

    private int UserID;

    private int OrderID;

    private String content;

    private String status;

    private String time;

    public Notifications(int ID, int UserID, int OrderID, String content, String status, String time) {
        this.ID = ID;
        this.UserID = UserID;
        this.OrderID = OrderID;
        this.content = content;
        this.status = status;
        this.time = time;
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

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
