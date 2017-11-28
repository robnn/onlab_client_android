package kurovszky.robin.unicalendar.web_service.model;


import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class Subject implements KvmSerializable {
    private Long id;
    private String name;
    private Long instituteId;
    private int semester;

    public Subject() {
    }

    public Subject(Long id, String name, Long instituteId, int semester) {
        this.id = id;
        this.name = name;
        this.instituteId = instituteId;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return id;
            case 1:
                return name;
            case 2:
                return instituteId;
            case 3:
                return semester;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                id = (Long) value;
                break;
            case 1:
                name = (String) value;
                break;
            case 2:
                instituteId = (Long) value;
                break;
            case 3:
                semester = (int) value;
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "name";
                break;
            case 2:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "instituteId";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "semester";
                break;
        }
    }
}
