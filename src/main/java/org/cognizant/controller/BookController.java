package org.cognizant.controller;

import org.cognizant.api.Book;
import org.cognizant.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "book/get", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(readOnly = true)
    public String get(@RequestParam long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return "Found Book: " + book;
    }

    @RequestMapping(value = "book/add", method = RequestMethod.GET)
    @ResponseBody
    public String add(@RequestParam long bookId, @RequestParam String title, @RequestParam double price,
                      @RequestParam int volume, @RequestParam String publishDate) {
        Book book = new Book(bookId, title, price, volume, LocalDate.parse(publishDate, DateTimeFormatter.ISO_DATE));
        bookRepository.save(book);
        return "Successfully Added " + book;
    }

    @RequestMapping(value = "book/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@RequestParam long bookId) {
        bookRepository.deleteById(bookId);
        return "Successfully Deleted: " + bookId;
    }
}
