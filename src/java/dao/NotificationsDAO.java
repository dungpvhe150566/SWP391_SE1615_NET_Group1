package dao;

import entity.Notifications;
import java.util.ArrayList;

public interface NotificationsDAO {
    public ArrayList<Notifications> getListNotifications() throws Exception;
    public ArrayList<Notifications> getListNotificationByUser(int userID) throws Exception;
    
}
