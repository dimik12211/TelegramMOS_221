package Application.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "repetitions")
    private String Repetitions;

    @Column(name = "quantity")
    private String Quantity;

    @Column(name = "comment")
    private String Comment;

    @Column(name = "ringWork")
    private String ringWork;

    @Column(name = "comment2")
    private String Comment2;

    public Workout() {

    }

    public Workout(String Name, String Repetitions, String Quantity, String Comment, String ringWork, String Comment2) {
        this.Name = Name;
        this.Repetitions = Repetitions;
        this.Quantity = Quantity;
        this.Comment = Comment;
        this.ringWork = ringWork;
        this.Comment2 = Comment2;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRepetitions() {
        return Repetitions;
    }

    public void setRepetitions(String repetitions) {
        Repetitions = repetitions;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getRingWork() {
        return ringWork;
    }

    public void setRingWork(String ringWork) {
        this.ringWork = ringWork;
    }

    public String getComment2() {
        return Comment2;
    }

    public void setComment2(String comment2) {
        Comment2 = comment2;
    }
}
