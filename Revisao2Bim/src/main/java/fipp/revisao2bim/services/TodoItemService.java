package fipp.revisao2bim.services;

import fipp.revisao2bim.entities.TodoItem;
import fipp.revisao2bim.entities.User;
import fipp.revisao2bim.entities.dtos.CreateTodoItemDto;
import fipp.revisao2bim.repositories.ITodoItemRepository;
import fipp.revisao2bim.repositories.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private ITodoItemRepository todoItemRepository;
    private IUserRepository userRepository;

    public TodoItemService(ITodoItemRepository todoItemRepository, IUserRepository userRepository) {
        this.todoItemRepository = todoItemRepository;
        this.userRepository = userRepository;
    }

    public TodoItem createTodoItem(CreateTodoItemDto dto) {
        if (dto.getUser() == null || dto.getUser().getId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        User user = userRepository.findById((long)dto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TodoItem todo = new TodoItem();
        todo.setContent(dto.getContent());
        todo.setDate(dto.getDate());
        todo.setStatus(dto.getStatus());
        todo.setUser_id(user);

        return todoItemRepository.save(todo);
    }
}
