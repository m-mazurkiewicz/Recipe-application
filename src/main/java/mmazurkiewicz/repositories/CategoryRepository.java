package mmazurkiewicz.repositories;

import mmazurkiewicz.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
