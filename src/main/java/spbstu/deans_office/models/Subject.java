package spbstu.deans_office.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Subject")
@Table(name = "subjects")
public class Subject {
    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    @Column(name = "subject_id")
    private Long subject_id = 0L;
    @Column(name = "name")
    private String name = "";

    @OneToMany(mappedBy = "subject")
    private Set<Mark> marks;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public String getName() {
        return name;
    }
}
