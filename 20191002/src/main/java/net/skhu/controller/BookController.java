package net.skhu.controller;

import net.skhu.domain.Book;
import net.skhu.domain.Category;
import net.skhu.domain.Publisher;
import net.skhu.repository.BookRepository;
import net.skhu.repository.CategoryRepository;
import net.skhu.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @RequestMapping(value="books", method= RequestMethod.GET)
    public List<Book> books(){
        return bookRepository.findAll();
    }

    @RequestMapping(value="categories", method= RequestMethod.GET)
    public List<Category> categories(){
        return categoryRepository.findAll();
    }

    @RequestMapping("publishers")
    public List<Publisher> publishers() {
        return publisherRepository.findAll();
    }

    // category 값이 {id} 인 book 레코드들을 출력
    @RequestMapping("category/{id}/books")
    public List<Book> booksByCategory(@PathVariable("id") int id) {
        return categoryRepository.findById(id).get().getBooks();
    }

}
