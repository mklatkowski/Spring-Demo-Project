package mklatkowski.learningApp.controller;

import mklatkowski.learningApp.model.Word;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

@Service
public class WordValidator {

    public boolean checkWord(String word) throws IOException {
        URL url = new URL("https://pl.pons.com/t%C5%82umaczenie/angielski-polski/"+word);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();

        return code != 404;
    }
}
