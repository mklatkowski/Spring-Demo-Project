package mklatkowski.learningApp.controller.word;

import mklatkowski.learningApp.model.Word;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(name = "/learn")
@RestController
public class LearnWordController {

    @GetMapping
    void learnAllUndoneWords(){
    }
}
