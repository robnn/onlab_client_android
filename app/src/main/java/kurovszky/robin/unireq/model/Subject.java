package kurovszky.robin.unireq.model;


import java.util.List;

public class Subject {
    private String name;
    private Requirement req;
    public Subject(String n, Requirement r){
        req = r;
        name = n;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(" - ");
        stringBuilder.append(req.toString());
        return stringBuilder.toString();
    }
}
