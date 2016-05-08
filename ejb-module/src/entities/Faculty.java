package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Class for storing data for entity Faculty.
 */
@Entity
@Table(name = "faculties", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
        @NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f"),
        @NamedQuery(name = "Faculty.findByName", query = "SELECT f FROM Faculty f WHERE f.name = :name")
})
public class Faculty implements Serializable {
    @Id
    @Column(name = "facid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "plan")
    private int plan;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private List<Applicant> applicants;
    @ManyToMany
    @JoinTable(name = "subj_for_facult",
            joinColumns = @JoinColumn(name = "facid", referencedColumnName = "facid"),
            inverseJoinColumns = @JoinColumn(name = "subid", referencedColumnName = "subid"))
    private List<Subject> subjects;

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

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
