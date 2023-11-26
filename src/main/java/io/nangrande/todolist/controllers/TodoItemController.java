package io.nangrande.todolist.controllers;

import io.nangrande.todolist.models.TodoItem;
import io.nangrande.todolist.repositories.TodoItemRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;

@Controller
public class TodoItemController {
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    private final TodoItemRepository todoItemRepository;

    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }

        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }
}
