package kurovszky.robin.unireq.model;

import java.util.List;

public class Requirement {
    private List<ReqElement> reqs;
    public  Requirement(List l){
        reqs = l;
    }
    @Override
    public String toString(){
        String b = null;
        if(reqs!=null){
            StringBuilder stringBuilder= new StringBuilder();
            stringBuilder.append("Reqs.: ");
            for (ReqElement a:reqs
                 ) {stringBuilder.append(a.toString());
                stringBuilder.append(", ");

            }
            b=stringBuilder.toString();
        }
        return  b;
    }
}
