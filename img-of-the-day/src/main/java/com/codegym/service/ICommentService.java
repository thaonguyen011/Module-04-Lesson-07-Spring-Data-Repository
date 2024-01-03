package com.codegym.service;

import com.codegym.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();
    Optional<Comment> findById(Long id);
    void save(Comment comment);
    void remove(Long id);
    int like(Long id);
    Page<Comment> findAll(Pageable pageable);
}
