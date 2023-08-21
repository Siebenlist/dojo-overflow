package com.example.dojooverflow.services;

import com.example.dojooverflow.models.TagQuestion;
import com.example.dojooverflow.repositories.TagQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class TagQuestionService{
    private final TagQuestionRepository tagQuestionRepository;

    public TagQuestionService(TagQuestionRepository tagQuestionRepository) {
        this.tagQuestionRepository = tagQuestionRepository;
    }

    public TagQuestion saveTagQuestion(TagQuestion tagQuestion){
        return tagQuestionRepository.save(tagQuestion);
    }
}