package entities;

import javax.persistence.*;

/**
 * Class represents record in sheet table
 */
@Entity
@Table(name = "sheet")
@NamedQueries({
        @NamedQuery(name = "SheetRecord.findByApplicantSubject",
                query = "SELECT s FROM SheetRecord s WHERE s.applicant = :applicant AND s.subject = :subject"),
        @NamedQuery(name = "SheetRecord.findRecordsForApplicant",
                query = "SELECT s FROM SheetRecord s WHERE s.applicant = :applicant")
})
public class SheetRecord {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "mark")
    private double mark;
    @ManyToOne
    @JoinColumn(name = "appid")
    private Applicant applicant;
    @ManyToOne
    @JoinColumn(name = "subid")
    private Subject subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
