package spbstu.deans_office.model;

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
@Table(name = "marks")
public class Mark {
    @Id
    @SequenceGenerator(
            name = "mark_seq_gen",
            sequenceName = "mark_seq_gen",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mark_seq_gen"
    )
    @Column(name = "mark_id")
    private Long mark_id = 0L;

    @ManyToOne
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "product_student_id_fkey"))
    private Person student;

    @ManyToOne
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "subject_teacher_id_fkey"))
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "product_teacher_id_fkey"))
    private Person teacher;

    @Column(name = "value")
    private Integer value;
}
