package kurovszky.robin.unicalendar.model;

import com.orm.SugarRecord;

import java.util.Calendar;

/**
 * Created by robin on 2016. 11. 26..
 */

public class Requirement extends SugarRecord{
    private String type;
    private Calendar time;
    private int hardiness; //1-5 number
    private Subject subject;
    private Calendar time_for_notification;

    public Requirement(String type, Calendar time, int hardiness) {
        this.type = type;
        this.time = time;
        this.hardiness = hardiness;

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time.getTime().toString();
    }

    public int getHardiness() {
        return hardiness;
    }

    public String getSubject() {
        return subject.getName();
    }
}
