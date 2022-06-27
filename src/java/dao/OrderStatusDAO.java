package dao;

import entity.OrderStatus;
import java.util.Vector;

public interface OrderStatusDAO {
    public Vector<OrderStatus> getOrderStatusList() throws Exception;
    public Vector<OrderStatus> getORderStatusList(int startRow, int endRow) throws Exception;
}
