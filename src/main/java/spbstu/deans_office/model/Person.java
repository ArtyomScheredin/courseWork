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

@Entity(name="Person")
@Table(name="people")
public class Person {

    @Id
    @SequenceGenerator(
            name ="person_sequence",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_generator"
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
    @JoinColumn(name="group_id", foreignKey=@ForeignKey(name = "person_group_id_fkey"))
    private Group group_id;
    @Column(name = "type")
    private Character type;

    public Person(String first_name, String last_name, String patronymic, Group group_id, Character type) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.group_id = group_id;
        this.type = type;
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

    public Group getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Group group_id) {
        this.group_id = group_id;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }
}
