package entities;

import java.io.Serializable;
import java.util.List;

/**
 * Class for storing data for entity Subject.
 */
public class Subject implements Serializable {
    private int id;
    private String name;
    private List<SheetRecord> exams;
    private List<Faculty> faculties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SheetRecord> getExams() {
        return exams;
    }

    public void setExams(List<SheetRecord> exams) {
        this.exams = exams;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
