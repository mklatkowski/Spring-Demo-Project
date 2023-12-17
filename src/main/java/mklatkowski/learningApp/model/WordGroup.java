package mklatkowski.learningApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "categories")
public class WordGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Pole wymagane")
    private String name;
    private boolean done;
    private int ratio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Word> words;


    public WordGroup(){

    }

    public WordGroup(String name){
        this.name = name;
        ratio = 0;
        done = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public void addWord(Word word){
        words.add(word);
    }

    public void calculateRatio(){
        ratio = words.stream().filter(Word::isDone).toList().size()*100/ words.size();
//        System.out.println("Wyliczone ratio:" +ratio);
    }
}
