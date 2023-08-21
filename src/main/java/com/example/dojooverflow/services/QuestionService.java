package com.example.dojooverflow.services;

import com.example.dojooverflow.models.Question;
import com.example.dojooverflow.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        return (Question) questionRepository.save(question);
    }

    public Question getQuestionById(Long id) {
        return (Question) questionRepository.findById(id).orElse(null);
    }
}