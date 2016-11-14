package kurovszky.robin.unicalendar.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReqElement {
    public enum reqtype {TEST, HOMEWORK, CONSULTATION}




    private reqtype re;
    private Calendar calendar;
    private String thematics;
    private int hardness;

    public reqtype getRe() {
        return re;
    }
    public void setRe(reqtype re) {
        this.re = re;
    }


    public Calendar getCalendar() {
        return calendar;
    }

    public String getThematics() {

        return thematics;
    }

    public ReqElement(String date, String thematic, int hardness) throws ParseException {
        calendar = Calendar.getInstance();
        parseDateString(date);
        thematics = thematic;
        this.hardness = hardness;

    }
    private void parseDateString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        calendar.setTime(dateFormat.parse(date));
    }
    @Override
    public String toString(){
        return(re.toString() + " határidő:" + calendar.getTime().toString() + " tematika: " + thematics + " -- nehezseg: " + hardness); //FIXME beégetett stringek.
    }
}
