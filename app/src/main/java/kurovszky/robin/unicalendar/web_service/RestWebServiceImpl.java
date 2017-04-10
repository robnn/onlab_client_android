package kurovszky.robin.unicalendar.web_service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.web_service.model.Comment;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okio.Buffer;

/**
 * Created by robin on 2017. 03. 15..
 */

public class RestWebServiceImpl implements WebService {
    public static final String baseURL = "robinandroidrest.hopto.org";
    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private Gson gson = new Gson();
    private User user;
    private static RestWebServiceImpl instance=null;

    public RestWebServiceImpl(User user) {
        this.user = user;
        authenticate(this.user);
    }

    public static WebService getInstance(User u) {
        if(instance == null)
            instance = new RestWebServiceImpl(u);
        return instance;
    }

    @Override
    public void authenticate(final User u) {
        if(user != null) {
            client = new OkHttpClient.Builder().authenticator(new Authenticator() {
                @Override
                public Request authenticate(Route route, Response response) throws IOException {
                    String credential = Credentials.basic(u.getUserName(), u.getPassword());
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
            }).build();
        }
        else
            client = new OkHttpClient();
    }

    @Override
    public Institute getInstituteById(Long id) {
        Institute institute = null;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("institute").addPathSegment(id.toString()).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null)
            institute = gson.fromJson(response.body().charStream(), Institute.class);
        return institute;
    }

    @Override
    public List<Institute> getInstitutes() {
        List<Institute> institutes = null;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("institute").addPathSegment("").build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type instituteListType = new TypeToken<ArrayList<Institute>>(){}.getType();
        if(response!=null)
            institutes = gson.fromJson(response.body().charStream(), instituteListType);
        return institutes;
    }

    @Override
    public void addInstitute(Institute i) {
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("institute").addPathSegment("").build();
        Request request = new Request.Builder().url(httpUrl).post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(i))).build();
        Response response = null;
        try {
            response =  client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User register(User u) {
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("user").addPathSegment("").build();
        User user = u;
        user.setId(99999L);
        Request request = new Request.Builder().url(httpUrl).post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(user))).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String id = response.header("Location");
        id = id.substring(56);
        user.setId(Long.parseLong(id));
        return user;
    }

    //only with admin
    @Override
    public User getUserByName(String name) {
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("user").addPathSegment("byname").addPathSegment(name).build();
        Request request = new Request.Builder().url(httpUrl).build();
        User user = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null)
            user = gson.fromJson(response.body().charStream(), User.class);
        return user;
    }

    @Override
    public String getNameById(long id) {
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("user").addPathSegment("namebyid").addPathSegment(String.valueOf(id)).build();
        Request request = new Request.Builder().url(httpUrl).build();
        String username = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null)
            try {
                username = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return username;
    }

    @Override
    public long getIdByName(User u) {
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("user").addPathSegment("idbyname").addPathSegment(u.getUserName()).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long toReturn = 0;
        try {
            toReturn = Long.parseLong(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public long getInstituteIdByName(User u) {
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("user").addPathSegment("instituteidbyname").addPathSegment(u.getUserName()).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return Long.parseLong(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  0L;
    }


    @Override
    public List<Subject> getSubjectsByInstitute(Institute i) {
        List<Subject> toReturn = null;
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("subject").addPathSegment("byinstitute").addPathSegment(i.getName()).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type subjectListType = new TypeToken<ArrayList<Subject>>(){}.getType();
        if(response!=null)
            toReturn = gson.fromJson(response.body().charStream(), subjectListType);
        return toReturn;

    }

    @Override
    public Subject getSubjectByName(String name) {
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("subject").addPathSegment("byname").addPathSegment(name).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Subject subject = null;
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response!=null)
            subject = gson.fromJson(response.body().charStream(), Subject.class);
        return subject;
    }

    @Override
    public void addSubject(Subject s) {
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("subject").addPathSegment("").build();
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(s));

        Request request = new Request.Builder().url(httpUrl).post(requestBody).build();
        try {

            final Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            String sss =  buffer.readUtf8();
        } catch (final IOException e) {
            ;
        }
        Response response = null;
        try {
            response =  client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> getCommentsBySubject(Subject s){
        List<Comment> toReturn = null;
        String url = baseURL;
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(url).port(8081).addPathSegment("Onlab-REST").addPathSegment("comment").addPathSegment("bysubject").addPathSegment(s.getName()).build();
        Request request = new Request.Builder().url(httpUrl).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type commentListType = new TypeToken<ArrayList<Comment>>(){}.getType();
        if(response!=null)
            toReturn = gson.fromJson(response.body().charStream(), commentListType);
        return toReturn;
    }

    @Override
    public void addComment(Comment c) {
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(baseURL).port(8081).addPathSegment("Onlab-REST").addPathSegment("comment").addPathSegment("").build();
        Request request = new Request.Builder().url(httpUrl).post(RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(c))).build();
        Response response = null;
        try {
            response =  client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
