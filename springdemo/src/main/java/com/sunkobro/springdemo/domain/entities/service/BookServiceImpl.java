package com.sunkobro.springdemo.domain.entities.service;


import com.sunkobro.springdemo.domain.entities.Author;
import com.sunkobro.springdemo.domain.entities.Book;
import com.sunkobro.springdemo.domain.entities.Category;
import com.sunkobro.springdemo.domain.entities.enums.AgeRestriction;
import com.sunkobro.springdemo.domain.entities.enums.EditionType;
import com.sunkobro.springdemo.domain.entities.repository.AuthorRepository;
import com.sunkobro.springdemo.domain.entities.repository.BookRepository;
import com.sunkobro.springdemo.domain.entities.repository.CategoryRepository;
import com.sunkobro.springdemo.domain.entities.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    public static final String BOOKS_FILE_PATH =
            "C:\\Users\\sunk0\\IdeaProjects\\springdemo\\src\\main\\resources\\books.txt";
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
@Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;

    this.authorRepository = authorRepository;
    this.categoryRepository = categoryRepository;
}

    @Override
    public void seedBooks() throws IOException {
        if(this.bookRepository.count() != 0){
            return;
        }
        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String s : booksFileContent) {
            String[] lineParams = s.split("\\s+");
            Book book = new Book();

            book.setAuthor(this.getRandomAuthor());
            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);
            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);
            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);
            double price = Double.parseDouble(lineParams[3]);
            book.setPrice(price);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 5; i < lineParams.length ; i++) {
                stringBuilder.append(lineParams[i]).append(" ");
            }
            book.setTitle(stringBuilder.toString().trim());
            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);
            this.bookRepository.saveAndFlush(book);
        }

    }
    private Author getRandomAuthor(){
    Random random = new Random();
    int randomId = random.nextInt((int) this.authorRepository.count());
    if(randomId != 0){
        return this.authorRepository.findById(randomId).orElse(null);
    }

    return null;
    }
    private Category getRandomCategory(){
        Random random = new Random();
        int randomId = random.nextInt((int) this.categoryRepository.count());
        if(randomId != 0){
            return this.categoryRepository.findById(randomId).orElse(null);

        }

        return null;
    }
    private Set<Category> getRandomCategories(){
    Set<Category> categories = new LinkedHashSet<>();
    Random random = new Random();
    int length = random.nextInt(5);
        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();
            categories.add(category);
        }
        return categories;
    }
}
