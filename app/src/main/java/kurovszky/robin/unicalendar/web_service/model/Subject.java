package kurovszky.robin.unicalendar.web_service.model;


public class Subject {
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
}
