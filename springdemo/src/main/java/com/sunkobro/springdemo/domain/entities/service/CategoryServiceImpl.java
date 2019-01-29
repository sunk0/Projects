package com.sunkobro.springdemo.domain.entities.service;

import com.sunkobro.springdemo.domain.entities.Category;
import com.sunkobro.springdemo.domain.entities.repository.CategoryRepository;
import com.sunkobro.springdemo.domain.entities.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    public static final String CATEGORIES_FILE_PATH =
            "C:\\Users\\sunk0\\IdeaProjects\\springdemo\\src\\main\\resources\\categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count() != 0){
            return;
        }
        String[] categoriesFileContent = this.fileUtil.getFileContent(CATEGORIES_FILE_PATH);
        for (String s : categoriesFileContent) {
            Category category = new Category();
            category.setName(s);
            this.categoryRepository.saveAndFlush(category);
        }
    }
}
