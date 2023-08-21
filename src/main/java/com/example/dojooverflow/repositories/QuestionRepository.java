package com.example.dojooverflow.repositories;

import com.example.dojooverflow.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
