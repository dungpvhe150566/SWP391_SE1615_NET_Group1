package entity;

public class Feedback {

    private int ID;

    private int UserID;

    private String UserName;

    private int ProductID;

    private int OrderID;

    private String DateFeedback;

    private int Star;

    private String FeedbackDetails;
    private Users user;
    private Product product;

    public Feedback() {
    }

    public Feedback(int ID, int UserID, String UserName, int ProductID, int OrderID, String DateFeedback, int Star, String FeedbackDetails) {
        this.ID = ID;
        this.UserID = UserID;
        this.UserName = UserName;
        this.ProductID = ProductID;
        this.OrderID = OrderID;
        this.DateFeedback = DateFeedback;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
    }

    public Feedback(int ID, String UserName, String DateFeedback, int Star, String FeedbackDetails) {
        this.ID = ID;
        this.UserName = UserName;
        this.DateFeedback = DateFeedback;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
    }

    public Feedback(int ID, int UserID, int ProductID, int OrderID, int Star, String FeedbackDetails) {
        this.ID = ID;
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.OrderID = OrderID;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
    }

    public Users getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getID() {
        return ID;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getDateFeedback() {
        return DateFeedback;
    }

    public void setDateFeedback(String DateFeedback) {
        this.DateFeedback = DateFeedback;
    }

    public int getStar() {
        return Star;
    }

    public void setStar(int Star) {
        this.Star = Star;
    }

    public String getFeedbackDetails() {
        return FeedbackDetails;
    }

    public void setFeedbackDetails(String FeedbackDetails) {
        this.FeedbackDetails = FeedbackDetails;
    }

    @Override
    public String toString() {
        return ID + " " + UserName + " " + DateFeedback + " " + Star + " " + FeedbackDetails;
    }

    

}
