package entities;

import java.io.Serializable;
import java.util.List;

/**
 * Class for storing data for entity Applicant.
 */
public class Applicant implements Serializable {
    private int id;
    private String name;
    private boolean entered;
    private double total;
    private Faculty faculty;
    private List<SheetRecord> exams;

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

    public boolean isEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<SheetRecord> getExams() {
        return exams;
    }

    public void setExams(List<SheetRecord> exams) {
        this.exams = exams;
    }
}
