package mklatkowski.learningApp.repository;

import mklatkowski.learningApp.model.WordGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordGroupRepository {

    List<WordGroup> findAll();

    Optional<WordGroup> findById(Integer id);

    WordGroup save(WordGroup entity);

    boolean existsById(Integer id);


}
