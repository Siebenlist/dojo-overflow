package com.example.dojooverflow.services;

import com.example.dojooverflow.models.Answer;
import com.example.dojooverflow.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswerByQuestion(Long questionId){
        return answerRepository.findAllById(questionId);
    }

}