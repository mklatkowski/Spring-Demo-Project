package mklatkowski.learningApp.sqlRepository;

import mklatkowski.learningApp.model.WordGroup;
import mklatkowski.learningApp.repository.WordGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlWordGroupRepository extends JpaRepository<WordGroup, Integer>, WordGroupRepository {

}
