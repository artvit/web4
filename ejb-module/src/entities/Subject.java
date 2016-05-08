package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Class for storing data for entity Subject.
 */
@Entity
@Table(name = "subjects")
@NamedQueries({
        @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name")
})
public class Subject implements Serializable {
    @Id
    @Column(name = "subid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private List<SheetRecord> exams;
    @ManyToMany(mappedBy = "subjects")
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
