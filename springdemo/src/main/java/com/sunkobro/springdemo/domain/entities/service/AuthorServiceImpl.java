package com.sunkobro.springdemo.domain.entities.service;

import com.sunkobro.springdemo.domain.entities.Author;
import com.sunkobro.springdemo.domain.entities.repository.AuthorRepository;
import com.sunkobro.springdemo.domain.entities.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {
    public static final String AUTHORS_FILE_PATH =
            "C:\\Users\\sunk0\\IdeaProjects\\springdemo\\src\\main\\resources\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;
@Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count() != 0){
            return;
        }
        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String s : authorFileContent) {
            String[] names = s.split("\\s+");
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            this.authorRepository.saveAndFlush(author);
        }
    }
}
