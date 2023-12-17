package mklatkowski.learningApp.service;

//import mklatkowski.learningApp.controller.wordGroup.WordGroupController;
import mklatkowski.learningApp.model.Word;
import mklatkowski.learningApp.model.WordGroup;
import mklatkowski.learningApp.repository.WordGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WordGroupService {

    private final WordGroupRepository repository;

    WordGroupService(WordGroupRepository repository){
        this.repository = repository;
    }

    public WordGroup createGroup(WordGroup toSave){
        return repository.save(toSave);
    }

    public List<WordGroup> findAllGroups(){
        return repository.findAll();
    }

    public Optional<WordGroup> findGroupById(int id){
        return repository.findById(id);
    }

    public boolean existsById(int id){
        return repository.existsById(id);
    }

    @Transactional
    public Optional<WordGroup> toggleGroup(int id){
        if(!repository.existsById(id)){
            return Optional.empty();
        }
        return repository.findById(id).map(group -> {
            group.setDone(!group.isDone());
            return group;
        });

    }

    public void addWord(Word word){
        int groupId = word.getGroup().getId();
        repository.findById(groupId).ifPresent(group -> group.addWord(word));
    }

    @Transactional
    public Optional<WordGroup> patchRatio(int groupId){
        if (!repository.existsById(groupId)) {
            return Optional.empty();
        }
        return repository.findById(groupId).map(group -> {
            group.calculateRatio();
            return group;
        });
    }
}
