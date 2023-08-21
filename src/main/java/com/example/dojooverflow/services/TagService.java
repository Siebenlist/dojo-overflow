package com.example.dojooverflow.services;

import com.example.dojooverflow.models.Tag;
import com.example.dojooverflow.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    public Tag createTag(Tag tag) {
        return (Tag) tagRepository.save(tag);
    }

}