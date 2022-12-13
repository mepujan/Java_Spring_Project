package BookStore.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookCacheConfig {
    @Bean
    public BookCache getBookCache(){
        return new BookCache();
    }
}
