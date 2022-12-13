package BookStore.books;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PageCountController.class)
public class PageCountControllerTest {
    @MockBean
    private BookRepository bookRepo;
    @MockBean
    private PageCounter pageCounter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBooksInfoByName() throws Exception{
        String bookName = "Python3";
        Books expectedResult = new Books(3,bookName,"Lambton",220,"Jim");
        when(bookRepo.findByName(bookName)).thenReturn(expectedResult);
        mockMvc.perform(get("/books?name=Python3")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedResult.getId()))
                .andExpect(jsonPath("$.name").value(expectedResult.getName()))
                .andExpect(jsonPath("$.author").value(expectedResult.getAuthor()))
                .andExpect(jsonPath("$.publication").value(expectedResult.getPublication()))
                .andDo(print());
    }


}
