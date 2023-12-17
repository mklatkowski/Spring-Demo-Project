package mklatkowski.learningApp.controller.word;

import mklatkowski.learningApp.controller.WordValidator;
import mklatkowski.learningApp.model.Word;
import mklatkowski.learningApp.model.WordGroup;
import mklatkowski.learningApp.service.WordGroupService;
import mklatkowski.learningApp.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/words")
@Controller
public class WordController {
    private final WordService service;
    private final WordValidator validator;
    private final WordGroupService wordGroupService;
    private final Logger logger = LoggerFactory.getLogger(WordController.class);

    public WordController(WordService service, WordValidator validator, WordGroupService wordGroupService){
        this.service = service;
        this.validator = validator;
        this.wordGroupService = wordGroupService;
    }

    @GetMapping
    String getAllWords(Model model){
        List<WordGroup> listGroups = wordGroupService.findAllGroups();
        model.addAttribute("groupsToChoose", listGroups);

        var word = new Word();
        model.addAttribute("word", word);
        logger.info("Every words shown");
        return "words";
    }

//    @GetMapping("/{id}")
//    ResponseEntity<?> getTaskById(@PathVariable int id){
//        var result = service.getSingleWord(id);
//        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    String getTaskById(@PathVariable int id, Model model){
        Optional<Word> singleWord = service.getSingleWord(id);
        if(singleWord.isEmpty()){
            model.addAttribute("message", "Słowo o podanym id nie znajduje się w bazie danych");
            return "wordPage";
        }
        model.addAttribute("singleWord", singleWord.get());
        return "wordPage";
    }


    @PostMapping("/{id}")
    String toggleWord(@PathVariable int id, Model model){

        if(service.toggleWord(id).isPresent()){
            wordGroupService.patchRatio(service.getSingleWord(id).get().getGroup().getId());
        }
        getTaskById(id, model);
        return "wordPage";
//
//
//        if(service.toggleWord(id).isEmpty()){
//            return "wordPage";
//        }
//        Optional<Word> singleWord = service.getSingleWord(id);
//        model.addAttribute("singleWord", singleWord.get());
//        return "wordPage";
    }


    @PostMapping
    String addWord(@ModelAttribute("word") @Valid Word current, BindingResult br, Model model) throws IOException {

        List<WordGroup> listGroups = wordGroupService.findAllGroups();
        model.addAttribute("groupsToChoose", listGroups);

        if(br.hasErrors()){
            return "words";
        }
        if(!validator.checkWord(current.getForeignMeaning())){
            model.addAttribute("message", "W bazie danych pons nie ma takiego słowa: " +current.getForeignMeaning());
            return "words";
        }
        if(service.existsByForeignMeaning(current.getForeignMeaning())){
            model.addAttribute("message", "Słowo: " +current.getForeignMeaning() +" znajduje się już w Twoim profilu");
            return "words";
        }


        Word result = service.saveWord(current);
        wordGroupService.addWord(current);
        model.addAttribute("word", new Word());
        model.addAttribute("words", getWords());
        model.addAttribute("message", "Dodano słowo");

        return "words";
    }

    @ModelAttribute("words")
    List<Word> getWords(){
        return service.getAllWords();
    }
}
