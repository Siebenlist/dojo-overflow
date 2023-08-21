package com.example.dojooverflow.repositories;

import com.example.dojooverflow.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findAllById(Long questionId);
}
