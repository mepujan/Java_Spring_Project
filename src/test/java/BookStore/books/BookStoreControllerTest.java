package BookStore.books;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.eq;

@WebMvcTest(BookController.class)
public class BookStoreControllerTest {
    @Autowired
    private MockMvc mockMVC;
    @MockBean
    private BookRepository bookRepo;

    @MockBean
    private PageCounter pageCounter;

    @Test
    public void createBookTest() throws Exception {
        mockMVC.perform(get("/createbook"))
                .andExpect(status().isOk())
                .andExpect(view().name("addNewBook"))
                .andExpect(model().attribute("book", new Books()))
                .andExpect(model().attribute("counter", pageCounter.getPageCounter()));
    }

    @Test
    public void createPostTest() throws Exception {
        Books expectedBook= new Books(null, "C Programming", "Lambton",220, "pujan");
        mockMVC.perform(post("/createbook").param("name", "C Programming")
                        .param("author", "pujan")
                        .param("publication", "Lambton")
                        .param("numberOfPages","220"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(MockMvcResultMatchers.flash().attribute("success","Book Added Successfully"));

        Mockito.verify(bookRepo).save(eq(expectedBook));
    }

    @Test
    public void createPostTest_InvalidBookInfo() throws Exception {
        Books expectedBook = new Books(null, "python", "lambton",220, "pujan");

        mockMVC.perform(post("/createbook").param("name", "java")
                        .param("publication", "lambton").
                        param("author", "pujan")
                        .param("numberOfPages","220"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/createbook"))
                .andExpect(MockMvcResultMatchers.flash().attribute("error","Cannot Add Book. Try Again.."));

        Mockito.verifyNoInteractions(bookRepo);
    }
    @Test
    public void listUserTest() throws Exception {
        List<Books> expectedList = new ArrayList<>();
        expectedList.add(new Books(null, "python", "lambton",220, "pujan"));

        Mockito.when(bookRepo.findAll()).thenReturn(expectedList);

        mockMVC.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("books", expectedList));
    }
}
