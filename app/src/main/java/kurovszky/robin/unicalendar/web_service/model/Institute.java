package kurovszky.robin.unicalendar.web_service.model;

/**
 * Created by robin on 2017. 03. 15..
 */

public class Institute {
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
}
