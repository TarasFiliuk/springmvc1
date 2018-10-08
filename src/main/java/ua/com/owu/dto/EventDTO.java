package ua.com.owu.dto;

import org.joda.time.DateTime;

import java.sql.Time;
import java.util.Date;

public class EventDTO {
    private String name; // G S

    private Date date; // G S

    private Time time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
