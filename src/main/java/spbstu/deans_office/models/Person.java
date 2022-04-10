package spbstu.deans_office.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Person")
@Table(name = "person")
public class Person {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    @Column(name = "person_id")
    private Long person_id = 0L;

    @Column(name = "first_name")
    private String first_name = "";

    @Column(name = "last_name")
    private String last_name = "";

    @Column(name = "patronymic")
    private String patronymic = "";

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "person_group_id_fkey"))
    private Group group;

    @Column(name = "type")
    private Character type;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Mark> marks_student;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Mark> marks_teacher;

    public Person() {
    }

    public Person(Long person_id, String first_name,
                  String last_name, String patronymic, Group group, Character type) {
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
       this.group = group;
        this.type = type;
    }

    public Person(String first_name, String last_name, String patronymic, Group group, Character type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
       this.group = group;
        this.type = type;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Group getGroup() {
        return group;
    }

   public void setGroup_id(Group group_id) {
        this.group = group;
    }

    public Type getType() {
        return (type == 's') ? Type.STUDENT : Type.TEACHER;
    }

    public void setType(Type type) {
        this.type = (type == Type.STUDENT) ? 's' : 't';
    }

    public enum Type {STUDENT, TEACHER}

    @Override
    public String toString() {
        return "Person{" +
               "person_id=" + person_id +
               ", first_name='" + first_name + '\'' +
               ", last_name='" + last_name + '\'' +
               ", patronymic='" + patronymic + '\'' +
               //", group=" + group.getName() +
               ", type=" + type +
               '}';
    }
}
