package io.nangrande.todolist.config;

import io.nangrande.todolist.models.TodoItem;
import io.nangrande.todolist.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoItemDataLoader implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    private final TodoItemRepository todoItemRepository;

    public TodoItemDataLoader(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    private void loadSeedData() {
        if (todoItemRepository.count() == 0) {
            TodoItem todoItem1 = new TodoItem("Сделать лабу по 1С");
            TodoItem todoItem2 = new TodoItem("Подготовить доклад о рыночной экономике");

            todoItemRepository.save(todoItem1);
            todoItemRepository.save(todoItem2);
        }
        logger.info("Количество задач: " + todoItemRepository.count());
    }
}
