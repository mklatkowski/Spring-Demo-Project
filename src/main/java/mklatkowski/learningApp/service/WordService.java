package mklatkowski.learningApp.service;

import mklatkowski.learningApp.model.Word;
import mklatkowski.learningApp.model.WordGroup;
import mklatkowski.learningApp.repository.WordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private final WordRepository repository;

    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public List<Word> getAllWords() {
        return repository.findAll();
    }

    public Optional<Word> getSingleWord(int id){
        return repository.findById(id);
    }

    @Transactional
    public Optional<Word> toggleWord(int id) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        return repository.findById(id).map(word -> {
            word.setDone(!word.isDone());
            return word;
        });
    }

    public Word saveWord(Word toSave){
        return repository.save(toSave);
    }

    public boolean existsByForeignMeaning(String foreignMeaning){
        return repository.existsByForeignMeaning(foreignMeaning);
    }

}
