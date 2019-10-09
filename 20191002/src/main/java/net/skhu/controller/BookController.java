package net.skhu.controller;

import net.skhu.domain.Book;
import net.skhu.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired BookMapper bookMapper;

    @RequestMapping(value="books", method=RequestMethod.GET)
    public List<Book> findAll(){
        List<Book> books = bookMapper.findAll();
        return books;
    }

}
