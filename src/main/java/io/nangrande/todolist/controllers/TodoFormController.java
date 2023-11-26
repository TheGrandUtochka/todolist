package io.nangrande.todolist.controllers;

import io.nangrande.todolist.models.TodoItem;
import io.nangrande.todolist.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);

    private final TodoItemRepository todoItemRepository;

    public TodoFormController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = todoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача № " + id + " не найдена"));
        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = todoItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Задача № " + id + " не найдена"));
        model.addAttribute("todo", todoItem);

        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "add-todo-item";
    }
}
