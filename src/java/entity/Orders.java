package entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Orders {

    private int ID;

    private int UserID;

    private float TotalPrice;

    private String Note;

    private int Status;

    private String DayBuy;

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

}
