package spbstu.deans_office.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id", columnDefinition = "bigserial")
    private Long markID;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Person student;

    @ManyToOne
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "subject_id_fkey"))
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "mark_teacher_id_fkey"))
    private Person teacher;

    @Column(name = "value", nullable = false)
    private Integer value;

    public Mark(Long markId, Person student, Subject subject, Person teacher, Integer value) {
        this.markID = markId;
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
        this.value = value;
    }

    public Mark(Person student, Subject subject, Person teacher, Integer value) {
        this.student = student;
        this.subject = subject;
        this.teacher = teacher;
        this.value = value;
    }

    public Mark() {
    }

    public Long getMarkID() {
        return markID;
    }

    public void setMarkID(Long mark_id) {
        this.markID = mark_id;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Mark{" +
               "markId=" + markID +
               ", student=" + student.getPersonId() +
               ", subject=" + subject.getSubjectId() +
               ", teacher=" + teacher.getPersonId() +
               ", value=" + value +
               '}';
    }
}
