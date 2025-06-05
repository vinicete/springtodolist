package fipp.revisao2bim.controllers;

import fipp.revisao2bim.entities.TodoItem;
import fipp.revisao2bim.entities.dtos.CreateTodoItemDto;
import fipp.revisao2bim.services.TodoItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todoitem")
public class TodoItemController {

    public TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @PostMapping
    public ResponseEntity<Object> createTodoItem(@RequestBody CreateTodoItemDto input) {
        try {
            TodoItem res = todoItemService.createTodoItem(input);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
