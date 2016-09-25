package kurovszky.robin.unireq.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReqElement {
    public enum reqtype {TEST, HOMEWORK, CONSULTATION}


    public void setRe(reqtype re) {
        this.re = re;
    }

    private reqtype re;
    private Calendar calendar;
    private String thematics;

    public reqtype getRe() {
        return re;
    }



    public Calendar getCalendar() {
        return calendar;
    }

    public String getThematics() {

        return thematics;
    }

    public ReqElement(String date, String thematic, reqtype r) throws ParseException {
        calendar = Calendar.getInstance();
        parseDateString(date);
        thematics = thematic;
        re = r;
    }
    private void parseDateString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        calendar.setTime(dateFormat.parse(date));
    }
    @Override
    public String toString(){
        return(re.toString() + " határidő:" + calendar.toString() + " tematika: " + thematics); //FIXME beégetett stringek.
    }
}
