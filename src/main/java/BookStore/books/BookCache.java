package BookStore.books;

import java.util.ArrayList;
import java.util.List;

public class BookCache {

    private final List<Books> storedBooks;

    public BookCache() {
        storedBooks = new ArrayList<>();
    }

    public void storeBooksInfo(Books book) {
        this.storedBooks.add(book);
    }

    public List<Books> getAllBookInfo() {
        return this.storedBooks;
    }
}
