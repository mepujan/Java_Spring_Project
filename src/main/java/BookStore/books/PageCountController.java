package BookStore.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageCountController {
    @Autowired
    private PageCounter pageCounter;

    @GetMapping("/currentcount")
    public Integer getCurrentCount(){
        return pageCounter.getPageCounter();
    }
}
