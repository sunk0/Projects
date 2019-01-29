package com.sunkobro.springdemo.domain.entities;

import com.sunkobro.springdemo.domain.entities.enums.AgeRestriction;
import com.sunkobro.springdemo.domain.entities.enums.EditionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "books")
public class Book extends BaseEntity {
    private AgeRestriction ageRestriction;
    private Integer copies;
    private String description;
    private EditionType editionType;
    private double price;
    private LocalDate releaseDate;
    private String title;
    // Why author and set<category> properties?
    private Author author;
    private Set<Category> categories;

    public Book() {
    }
//Is enumerated a must?
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction")
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
@Column(name = "copies")
    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    @Column(name = "description",columnDefinition = "text",length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type")
    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //WHY MANYTOONE?, WHAT IS CASCADE?, DIFF BETWEEn JoinColumn - JoinColumns - JoinTable
@ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
@JoinColumn(name = "author_id",referencedColumnName = "id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    //WHY EXACTLY CATEGORY.class
@ManyToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
@JoinTable(name = "books_categories",
        joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"),
inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
