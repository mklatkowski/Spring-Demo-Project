package mklatkowski.learningApp.repository;

import mklatkowski.learningApp.model.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository {

    List<Word> findAll();

    Page<Word> findAll(Pageable page);

    List<Word> findByDone(boolean done);

    Optional<Word> findById(Integer id);

    Word save (Word word);

    boolean existsById(Integer id);

    boolean existsByForeignMeaning(String foreignMeaning);

}
