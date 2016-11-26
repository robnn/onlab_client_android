package kurovszky.robin.unicalendar.model;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robin on 2016. 11. 26..
 */

public class Subject extends SugarRecord{
    List<Requirement> requirements;
    String name;

    public Subject(String name) {
        this.name = name;
        requirements = new ArrayList<>();
    }
    public void addReq(Requirement requirement){
        requirement.setSubject(this);
        requirements.add(requirement);
    }

    public String getName() {
        return name;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
