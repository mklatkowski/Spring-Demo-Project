package mklatkowski.learningApp.controller.wordGroup;

import mklatkowski.learningApp.model.Word;
import mklatkowski.learningApp.model.WordGroup;
import mklatkowski.learningApp.service.WordGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/groups")
public class WordGroupController {

    private static final Logger logger = LoggerFactory.getLogger(WordGroupController.class);
    private final WordGroupService service;

    WordGroupController(WordGroupService service){
        this.service = service;
    }

    @PostMapping
    String addGroup(@ModelAttribute("group") @Valid WordGroup current, BindingResult br, Model model) throws IOException {
        if(br.hasErrors()){
            return "groups";
        }

        WordGroup result = service.createGroup(current);
        model.addAttribute("group", new WordGroup());
        model.addAttribute("groups", getGroups());
        model.addAttribute("message", "Dodano grupę");

        return "groups";
    }

    @GetMapping
    String getAllWords(Model model){
        var wordGroup = new WordGroup();
        model.addAttribute("group", wordGroup);
        logger.info("Every words shown");
        return "groups";
    }

//    @GetMapping("/{id}")
//    ResponseEntity<?> getGroupById(@PathVariable int id){
//        var result = service.findGroupById(id);
//        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    String getGroupById(@PathVariable int id, Model model){
        Optional<WordGroup> singleGroup = service.findGroupById(id);
        if(singleGroup.isEmpty()){
            model.addAttribute("message", "Grupa o podanym id nie znajduje się w bazie danych");
            return "groupPage";
        }
        model.addAttribute("singleGroup", singleGroup.get());
        model.addAttribute("wordlist", getWordsFromGroupWithId(id));
        return "groupPage";

    }

    @PatchMapping ("/{id}")
    ResponseEntity<WordGroup> toggleGroup(@PathVariable int id){
        if(service.toggleGroup(id).isEmpty()){
            logger.info("Task with id " +id +" doesn't exist");
            return ResponseEntity.notFound().build();
        }
        logger.info("Task with id " + id +" is shown");
        return ResponseEntity.noContent().build();
    }

    @ModelAttribute("groups")
    List<WordGroup> getGroups(){
        return service.findAllGroups();
    }

    @ModelAttribute("wordsInGroup")
    List<Word> getWordsFromGroupWithId(Integer id){
        if(id ==null){
            return null;
        }
        if(service.findGroupById(id).isPresent()){
            return new ArrayList<>(service.findGroupById(id).get().getWords());
        }
        return null;
    }

}
