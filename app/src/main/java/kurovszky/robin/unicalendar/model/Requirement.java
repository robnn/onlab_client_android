package kurovszky.robin.unicalendar.model;

import android.content.res.Resources;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import kurovszky.robin.unicalendar.R;

/**
 * Created by robin on 2016. 11. 26..
 */

public class Requirement extends SugarRecord  implements Comparable<Requirement>{
    private String type;
    private Calendar time;
    private int hardiness; //1-5 number
    private String subject;

    public Requirement() {
    }

    public Requirement(String type, Calendar time, int hardiness) {
        this.type = type;
        this.time = time;
        this.hardiness = hardiness;

    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy:MM:dd - HH:mm");
        String formatted = format1.format(time.getTime());
        return formatted;
    }
    public Date getTimeInDate(){
        return  time.getTime();
    }

    public int getHardiness() {
        return hardiness;
    }

    public String getSubject() {
        if(subject!=null)
            return subject;
        return "";
    }


    @Override
    public int compareTo(Requirement o) {
        if(getTimeInDate().getTime()> o.getTimeInDate().getTime())
            return 1;
        if(getTimeInDate().getTime() < o.getTimeInDate().getTime())
            return -1;

        return 0;
    }
}
