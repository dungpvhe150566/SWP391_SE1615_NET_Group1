package entity;

import java.sql.Date;

public class Feedback {

    private int ID;

    private int UserID;

    private String UserName;

    private int ProductID;
    private String ProductName;

    private Date DateFeedback;

    private int Star;

    private String FeedbackDetails;
    private Users user;
    private Product product;

    public Feedback() {
    }

    public Feedback(int ID, int UserID, String UserName, int ProductID,  Date DateFeedback, int Star, String FeedbackDetails) {
        this.ID = ID;
        this.UserID = UserID;
        this.UserName = UserName;
        this.ProductID = ProductID;
        this.DateFeedback = DateFeedback;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
    }

    public Feedback(int UserID, int ProductID,int Star, String FeedbackDetails,Date DateFeedback) {
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
        this.DateFeedback = DateFeedback;
    }

    public Feedback(int ID, int UserID, int ProductID, int Star, String FeedbackDetails) {
        this.ID = ID;
        this.UserID = UserID;
        this.ProductID = ProductID;
        this.Star = Star;
        this.FeedbackDetails = FeedbackDetails;
    }

    public Feedback(int ID, String userName, Date dayFeedBack, int star, String feedbackDetails) {
        this.ID = ID;
        this.UserName = userName;
        this.DateFeedback = dayFeedBack;
        this.Star = star;
        this.FeedbackDetails = feedbackDetails;
    }

    public Feedback(int ID, String Usname, String Pname, int star, String detail) {
        this.ID = ID;
        this.UserName = Usname;
        this.ProductName = Pname;
        this.Star = star;
        this.FeedbackDetails = detail;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
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

    public Date getDateFeedback() {
        return DateFeedback;
    }

    public void setDateFeedback(Date DateFeedback) {
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
        return ID + " " + UserName + " " + ProductName + " " + Star + " " + FeedbackDetails+"\n";
    }

    

}
