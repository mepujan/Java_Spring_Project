package BookStore.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PageCountController {
    @Autowired
    private PageCounter pageCounter;
    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/currentcount")
    public Integer getCurrentCount(){
        return pageCounter.getPageCounter();
    }

    @GetMapping("/books")
    public ResponseEntity<Books> getBookInfoByName(@RequestParam String name){
        Optional<Books> bookInfo = Optional.ofNullable(bookRepo.findByName(name));
        if(bookInfo.isPresent()){
            return new ResponseEntity<>(bookInfo.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
