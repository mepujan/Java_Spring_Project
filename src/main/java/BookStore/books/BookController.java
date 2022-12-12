package BookStore.books;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private PageCounter pageCounter;

    @GetMapping("/")
    public String listBookData(Model model){
        pageCounter.incrementPageCounter();
        model.addAttribute("counter",pageCounter.getPageCounter());
        model.addAttribute("books",bookRepo.findAll());
        return "index";
    }

    @GetMapping("/createbook")
    public String addNewBook(Model model){
        pageCounter.incrementPageCounter();
        model.addAttribute("book",new Books());
        model.addAttribute("counter",pageCounter.getPageCounter());
        return "addNewBook";
    }

    @PostMapping("/createbook")
    public String addNewBook(@Valid Books toSave, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error","Cannot Add Book. Try Again..");
            return "redirect:/createbook";
        }
        else {
            this.bookRepo.save(toSave);
            redirectAttributes.addFlashAttribute("success", "Book Added Successfully");
            return "redirect:/";
        }
    }
}
