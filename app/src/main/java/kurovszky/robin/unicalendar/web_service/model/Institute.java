package kurovszky.robin.unicalendar.web_service.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Hashtable;

/**
 * Created by robin on 2017. 03. 15..
 */

public class Institute implements KvmSerializable{

    private Long id;
    private String name;

    public Institute() {
    }

    public Institute(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Institute{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Object getProperty(int index) {
        switch (index){
            case 0:
                return id;
            case 1:
                return name;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index){
            case 0:
                id = (long)value;
                break;
            case 1:
                name = (String) value;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index){
            case 0:
                info.type = PropertyInfo.LONG_CLASS;
                info.name = "id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "name";
                break;
        }
    }

}
