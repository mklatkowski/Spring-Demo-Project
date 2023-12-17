package mklatkowski.learningApp.sqlRepository;

import mklatkowski.learningApp.model.Word;
import mklatkowski.learningApp.repository.WordRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlWordRepository extends JpaRepository<Word, Integer>, WordRepository {

}
