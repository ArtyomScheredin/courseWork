package spbstu.deans_office.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Mark")
@Table(name = "mark")
public class Mark {
    @Id
    @SequenceGenerator(
            name = "mark_sequence",
            sequenceName = "mark_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mark_sequence"
    )
    @Column(name = "mark_id")
    private Long mark_id;

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

    public Mark(Long mark_id, Person student, Subject subject, Person teacher, Integer value) {
        this.mark_id = mark_id;
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

    public Long getMark_id() {
        return mark_id;
    }

    public void setMark_id(Long mark_id) {
        this.mark_id = mark_id;
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
               "mark_id=" + mark_id +
               ", student=" + student.getLast_name() +
               ", subject=" + subject.getName() +
               ", teacher=" + teacher.getLast_name() +
               ", value=" + value +
               '}';
    }
}
