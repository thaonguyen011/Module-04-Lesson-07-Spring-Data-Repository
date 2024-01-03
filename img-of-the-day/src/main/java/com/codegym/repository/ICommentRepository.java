package com.codegym.repository;

import com.codegym.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface ICommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @Transactional
    @Modifying
    @Query("update Comment set likeVote = likeVote + 1 where id = :id")
    void like(@Param("id") Long id);
}
