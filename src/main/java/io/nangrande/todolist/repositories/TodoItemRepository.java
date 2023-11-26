package io.nangrande.todolist.repositories;

import io.nangrande.todolist.models.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

}
