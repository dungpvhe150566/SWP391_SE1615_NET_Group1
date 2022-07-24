package entity;

public class Message {
    private int Id;
    private int userID;
    private String message;
    private int roomId;
    private String date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    private String userName;

    public Message() {
    }

    public Message(int Id, int userID, String message, int roomId, String date) {
        this.Id = Id;
        this.userID = userID;
        this.message = message;
        this.roomId = roomId;
        this.date = date;
    }
    public Message(int Id, int userID,String name ,String message, int roomId, String date) {
        this.Id = Id;
        this.userID = userID;
        this.userName=name;
        this.message = message;
        this.roomId = roomId;
        this.date = date;
    }

    public Message(int userID, String message, int roomId, String date) {
        this.userID = userID;
        this.message = message;
        this.roomId = roomId;
        this.date = date;
    }
    public Message(int userID, int roomId, String name) {
        this.userID = userID;
        this.roomId = roomId;
        this.userName=name;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Id:"+Id;
    }
    
    
}
