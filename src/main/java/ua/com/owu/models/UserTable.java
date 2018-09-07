package ua.com.owu.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;
    private int capacity;/*кількість людей*/

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userTable")
    List<UserOrder> userOrders;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Place place;

    public UserTable() {
    }

    public UserTable(int capacity) {
        this.capacity = capacity;
    }

    public UserTable(int capacity, List<UserOrder> userOrders, Place place) {
        this.capacity = capacity;
        this.userOrders = userOrders;
        this.place = place;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "tableId=" + tableId +
                ", capacity=" + capacity +
                '}';
    }
}
