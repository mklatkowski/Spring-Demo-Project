package mklatkowski.learningApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message="Pole wymagane")
    private String foreignMeaning;
    @NotBlank(message="Pole wymagane")
    private String localMeaning;

    private boolean done;

    @ManyToOne
    private WordGroup group;

    public Word() {
    }

    Word(String foreign, String local){
        this.foreignMeaning = foreign;
        this.localMeaning = local;
        done = false;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForeignMeaning() {
        return foreignMeaning;
    }

    public void setForeignMeaning(String foreignMeaning) {
        this.foreignMeaning = foreignMeaning;
    }

    public String getLocalMeaning() {
        return localMeaning;
    }

    public void setLocalMeaning(String local) {
        this.localMeaning = local;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public WordGroup getGroup() {
        return group;
    }

    public void setGroup(WordGroup group) {
        this.group = group;
    }
}
