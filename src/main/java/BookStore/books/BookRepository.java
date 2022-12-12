package BookStore.books;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Books,Integer> {
    public Books findByName(String name);
}
