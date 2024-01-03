package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("")
    public ModelAndView index() {
//        Iterable<Comment> comments = commentService.findAll();
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("author")));
        Page<Comment> comments = commentService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(Comment comment) {
        commentService.save(comment);
        Iterable<Comment> comments = commentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        modelAndView.addObject("message", "Add comment successfully");
        return modelAndView;

    }

    @PostMapping("/{id}/like")
    public ModelAndView like(@PathVariable("id") Long id,  RedirectAttributes redirect) {
        commentService.like(id);
        return index();
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Order.asc("author")));
        Page<Comment> comments = commentService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }
}
