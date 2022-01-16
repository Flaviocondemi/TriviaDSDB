package com.example.ecommerce.DemoModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE q.difficulty = ?1 " +
            "AND q.type = ?2 and q.category = ?3 ")
    public Page<Question> findByCategoryAndDifficultyAndType(String difficulty, String type, String category, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.type = ?1 " +
            "and q.category = ?2 ")
    public Page<Question> findByTypeAndCategory(String type, String category, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.type = ?1 " +
            "and q.difficulty = ?2 ")
    public Page<Question> findByTypeAndDifficulty(String type, String category, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.difficulty = ?1 " +
            "and q.category = ?2 ")
    public Page<Question> findByDifficultyAndCategory(String type, String category, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.type = ?1")
    public Page<Question> findByType(String type, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.difficulty = ?1")
    public Page<Question> findByDifficulty(String type, Pageable amount);

    @Query("SELECT q FROM Question q WHERE q.category = ?1")
    public Page<Question> findByCategory(String type, Pageable amount);



}
