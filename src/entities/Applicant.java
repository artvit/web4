package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Class for storing data for entity Applicant.
 */
@Entity
@Table(name = "applicants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
        @NamedQuery(name = "Applicant.findAll", query = "SELECT a FROM Applicant a"),
        @NamedQuery(name = "Applicant.findByName", query = "SELECT a FROM Applicant a WHERE a.name = :name")
})
public class Applicant {
    @Id
    @Column(name = "appid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name",
            nullable = false)
    private String name;
    @Column(name = "entered")
    private boolean entered;
    @Column(name = "total")
    private double total;
    @ManyToOne
    @JoinColumn(name = "faculty", referencedColumnName = "facid")
    private Faculty faculty;
    @OneToMany(mappedBy = "applicant")
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
