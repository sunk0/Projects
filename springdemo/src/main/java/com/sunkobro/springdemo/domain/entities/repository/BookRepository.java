package com.sunkobro.springdemo.domain.entities.repository;

import com.sunkobro.springdemo.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
