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
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", columnDefinition = "bigserial")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "person_group_id_fkey"))
    private Group group;

    @Column(name = "type")
    private Character type;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Mark> marksStudent;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Mark> marksTeacher;

    public Person() {
    }

    public Person(Long person_id, String firstName,
                  String lastName, String patronymic, Group group, Character type) {
        this.personId = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.group = group;
        this.type = type;
    }

    public Person(String firstName, String lastName, String patronymic, Group group, Character type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.group = group;
        this.type = type;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
               "person_id=" + personId +
               ", first_name='" + firstName + '\'' +
               ", last_name='" + lastName + '\'' +
               ", patronymic='" + patronymic + '\'' +
               //", group=" + group.getName() +
               ", type=" + type +
               '}';
    }
}
