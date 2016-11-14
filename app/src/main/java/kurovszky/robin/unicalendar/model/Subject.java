package kurovszky.robin.unicalendar.model;


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
