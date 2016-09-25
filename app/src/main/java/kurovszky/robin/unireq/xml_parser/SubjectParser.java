package kurovszky.robin.unireq.xml_parser;


import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unireq.model.ReqElement;
import kurovszky.robin.unireq.model.Requirement;
import kurovszky.robin.unireq.model.Subject;

/**
 * Usage is:
 * getInstance()
 * setInputStream(..)
 * parse() witch will give us a List
 */
public class SubjectParser {
    private static final String PARSING_ERROR = "PARSING ERROR";
    private static SubjectParser ourInstance = new SubjectParser();
    private static XmlPullParser parser;
    private InputStream xmlInputStream;
    public static SubjectParser getInstance() {
        return ourInstance;
    }

    private SubjectParser() {
        parser = Xml.newPullParser();
        try {
            parser.setFeature(XmlPullParser.FEATURE_VALIDATION, true);
        } catch (XmlPullParserException e) {
            e.printStackTrace(); //TODO
        }
        xmlInputStream = null;
    }
    public Subject parse() throws XmlPullParserException, IOException {
       if(xmlInputStream!=null){
           try{
               parser.setInput(xmlInputStream, null);
               parser.nextTag();
               return readSubject(parser);
           } finally {
               xmlInputStream.close();
           }

       }
       else{
           Log.e(PARSING_ERROR, "maybe haven't set the inputstream before parse?");
           return null;
       }
    }
    public void setInputStream(InputStream a){
        xmlInputStream = a;
    }
    private Subject readSubject(XmlPullParser parser) throws IOException, XmlPullParserException {

        String subjectName = null;
        Subject subject ;
        Requirement requirement=null;

        parser.require(XmlPullParser.START_TAG, null, "subject");
        while(parser.next() !=XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("name")){
                subjectName = readName(parser);
            } else if(name.equals("requirement")){
                requirement = new Requirement(readReq(parser));
            }
            else{
                skip(parser);
            }
        }
        subject = new Subject(subjectName, requirement);
        return subject;
    }
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "name");
        return title;
    }
    private List readReq(XmlPullParser parser) throws IOException, XmlPullParserException {
        List<ReqElement> reqElements = new ArrayList<>();
        ReqElement rq = null;
        parser.require(XmlPullParser.START_TAG, null, "requirement");
        while(parser.next() !=XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("homework")){
                rq = readReqElement(parser, name);

            } else if(name.equals("test")){
                rq = readReqElement(parser, name);

            } else if(name.equals("consultation")){
                rq = readReqElement(parser, name);

            }
            else{
                skip(parser);
            }
            reqElements.add(rq);
        }
        return reqElements;
    }

    private ReqElement readReqElement(XmlPullParser parser, String n)throws IOException, XmlPullParserException {
        ReqElement reqElement = null;
        parser.require(XmlPullParser.START_TAG, null, n);
        try {
            reqElement= new ReqElement(readDate(parser), readThematics(parser),null);
        } catch (ParseException e) {
            e.printStackTrace(); //TODO
        }
        parser.require(XmlPullParser.END_TAG, null, n);
        switch (n){
            case "homework": reqElement.setRe(ReqElement.reqtype.HOMEWORK);
                break;
            case "test": reqElement.setRe(ReqElement.reqtype.TEST);
                break;
            case "consultation": reqElement.setRe(ReqElement.reqtype.CONSULTATION);
                break;
            default:
                break;
        }
        return reqElement;
    }
    private String readDate(XmlPullParser parser)throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "date");
        String date=readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "date");
        return date;

    }
    private String readThematics(XmlPullParser parser)throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "thematics");
        String thematics=readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "thematics");
        return thematics;

    }
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
