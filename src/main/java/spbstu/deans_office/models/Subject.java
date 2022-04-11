package spbstu.deans_office.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", columnDefinition = "bigserial")
    private Long subjectId;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Mark> marks;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subject{" +
               "subject id=" + subjectId +
               ", name='" + name + '\'' +
               '}';
    }
}
