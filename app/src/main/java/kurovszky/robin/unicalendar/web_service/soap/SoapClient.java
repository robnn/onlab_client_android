package kurovszky.robin.unicalendar.web_service.soap;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.serialization.PropertyInfo;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;

public class SoapClient {
    User user;
    private static final String NAMESPACE = "http://service/";
    private static String URL = "http://robnn.dynu.net:8888/ws/service";


    public User getUser() {
        return user;
    }

    public void setAuthData(User user) {
        this.user = user;
    }

    private List<HeaderProperty> authenticate() {
        List<HeaderProperty> headerProperties = new ArrayList<>();
        if (user != null && user.getUserName() != null && !user.getUserName().isEmpty() &&
                user.getPassword() != null && !user.getUserName().isEmpty()) {
            headerProperties.add(new HeaderProperty("Username", user.getUserName()));
            headerProperties.add(new HeaderProperty("Password", user.getPassword()));
        }
        return headerProperties;
    }

    public void addInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute) {
        String METHOD_NAME = "addInstitute";
        String SOAP_ACTION = "\"addInstitute\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexInstitutePorperty = new PropertyInfo();
        complexInstitutePorperty.setName("arg0");
        complexInstitutePorperty.setType(institute.getClass());
        complexInstitutePorperty.setValue(institute);


        request.addProperty(complexInstitutePorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public User register(User u) {
        String METHOD_NAME = "register";
        String SOAP_ACTION = "\"register\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexUserProperty = new PropertyInfo();
        complexUserProperty.setName("arg0");
        complexUserProperty.setType(u.getClass());
        complexUserProperty.setValue(u);


        request.addProperty(complexUserProperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        Long id = 0L;
        if (response != null) {
            id = Long.parseLong(response.getProperty("id").toString());
        }
        User toreturn = u;
        if (id != 0L)
            toreturn.setId(id);
        return toreturn;
    }

    public String getNamebyId(long id) {
        String METHOD_NAME = "getNameById";
        String SOAP_ACTION = "\"getNameById\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo idPorperty = new PropertyInfo();
        idPorperty.setName("arg0");
        idPorperty.setType(PropertyInfo.LONG_CLASS);
        idPorperty.setValue(id);


        request.addProperty(idPorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapPrimitive response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapPrimitive) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        String name = null;
        if (response != null) {
            name = response.getValue().toString();
        }
        return name;
    }

    public long getIdByName(String name) throws BaseException {
        String METHOD_NAME = "getIdByName";
        String SOAP_ACTION = "\"getIdByName\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo nameProperty = new PropertyInfo();
        nameProperty.setName("arg0");
        nameProperty.setType(String.class);
        nameProperty.setValue(name);


        request.addProperty(nameProperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapPrimitive response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapPrimitive) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        long id = 0;

        if (response != null) {
            id = Long.parseLong(response.getValue().toString());
            if (id == 0L)
                throw new BaseException(new ErrorObject(ErrorCode.LOGIN_FAILED));

        }
        return id;

    }

    public Institute getInstituteById(Long id) {
        String METHOD_NAME = "getInstitutebyId";
        String SOAP_ACTION = "\"getInstitutebyId\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexUserPorperty = new PropertyInfo();
        complexUserPorperty.setName("arg0");
        complexUserPorperty.setType(PropertyInfo.LONG_CLASS);
        complexUserPorperty.setValue(id);


        request.addProperty(complexUserPorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        Institute institute = new Institute();
        if (response != null) {
            institute.setId(Long.parseLong(response.getProperty(0).toString()));
            institute.setName(response.getProperty(1).toString());
        }
        return institute;


    }

    public long getInstituteIdByName(String name) {
        String METHOD_NAME = "getInstituteIdByName";
        String SOAP_ACTION = "\"getInstituteIdByName\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo nameProperty = new PropertyInfo();
        nameProperty.setName("arg0");
        nameProperty.setType(String.class);
        nameProperty.setValue(name);


        request.addProperty(nameProperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapPrimitive response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapPrimitive) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        long id = 0;
        if (response != null) {
            id = Long.parseLong(response.getValue().toString());
        }
        return id;
    }

    public List<Subject> getSubjectsByInstitute(kurovszky.robin.unicalendar.web_service.model.Institute institute) {
        String METHOD_NAME = "getSubjectsByInstitute";
        String SOAP_ACTION = "\"getSubjectsByInstitute\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        PropertyInfo complexInstitutePorperty = new PropertyInfo();
        complexInstitutePorperty.setName("arg0");
        complexInstitutePorperty.setType(institute.getClass());
        complexInstitutePorperty.setValue(institute);

        request.addProperty(complexInstitutePorperty);

        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        ArrayList<Subject> subjects = new ArrayList<>();
        int size = response.getPropertyCount();
        for (int i = 0; i < size; i++) {
            SoapObject actual = (SoapObject) response.getProperty(i);
            Subject subject = new Subject();
            subject.setId(Long.parseLong(actual.getProperty(0).toString()));
            subject.setName(actual.getProperty(1).toString());
            subject.setInstituteId(Long.parseLong(actual.getProperty(2).toString()));
            subject.setSemester(Integer.parseInt(actual.getProperty(3).toString()));
            subjects.add(subject);
        }
        return subjects;
    }

    public Subject getSubjectByName(String name) {
        String METHOD_NAME = "getSubjectByName";
        String SOAP_ACTION = "\"getSubjectByName\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo nameProperty = new PropertyInfo();
        nameProperty.setName("arg0");
        nameProperty.setType(String.class);
        nameProperty.setValue(name);


        request.addProperty(nameProperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        Subject subject = new Subject();
        if (response != null && response.getPropertyCount() == 4) {
            subject.setId(Long.parseLong(response.getProperty(0).toString()));
            subject.setName(response.getProperty(1).toString());
            subject.setInstituteId(Long.parseLong(response.getProperty(2).toString()));
            subject.setSemester(Integer.parseInt(response.getProperty(3).toString()));
            return subject;
        }
        return null;
    }

    public void addSubject(Subject subject) {
        String METHOD_NAME = "addSubject";
        String SOAP_ACTION = "\"addSubject\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexUserPorperty = new PropertyInfo();
        complexUserPorperty.setName("arg0");
        complexUserPorperty.setType(subject.getClass());
        complexUserPorperty.setValue(subject);


        request.addProperty(complexUserPorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentsBySubject(Subject subject) {
        String METHOD_NAME = "getCommentsBySubject";
        String SOAP_ACTION = "\"getCommentsBySubject\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexUserPorperty = new PropertyInfo();
        complexUserPorperty.setName("arg0");
        complexUserPorperty.setType(subject.getClass());
        complexUserPorperty.setValue(subject);


        request.addProperty(complexUserPorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        ArrayList<Comment> comments = new ArrayList<>();
        int size = response.getPropertyCount();
        for (int i = 0; i < size; i++) {
            SoapObject actual = (SoapObject) response.getProperty(i);
            Comment comment = new Comment();
            comment.setId(Long.parseLong(actual.getProperty(0).toString()));
            comment.setCommentText(actual.getProperty(1).toString());
            comment.setUserId(Long.parseLong(actual.getProperty(2).toString()));
            comment.setSubjectId(Long.parseLong(actual.getProperty(3).toString()));
            comments.add(comment);
        }
        return comments;
    }

    public void addComment(Comment comment) {
        String METHOD_NAME = "addComment";
        String SOAP_ACTION = "\"addComment\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo complexUserPorperty = new PropertyInfo();
        complexUserPorperty.setName("arg0");
        complexUserPorperty.setType(comment.getClass());
        complexUserPorperty.setValue(comment);


        request.addProperty(complexUserPorperty);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public List<Institute> getInstitutes() throws BaseException{
        String METHOD_NAME = "getAllInstitutes";
        String SOAP_ACTION = "\"getAllInstitutes\"";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        SoapObject response = null;
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope, authenticate());
            response = (SoapObject) envelope.getResponse();

        } catch (IOException | XmlPullParserException e) {
            throw new BaseException(new ErrorObject(ErrorCode.SERVER_DOWN));
        }

        ArrayList<Institute> institutes = new ArrayList<>();
        int size = response.getPropertyCount();
        for (int i = 0; i < size; i++) {
            SoapObject actual = (SoapObject) response.getProperty(i);
            Institute institute = new Institute();
            institute.setId(Long.parseLong(actual.getProperty(0).toString()));
            institute.setName(actual.getProperty(1).toString());
            institutes.add(institute);
        }
        return institutes;
    }
}
