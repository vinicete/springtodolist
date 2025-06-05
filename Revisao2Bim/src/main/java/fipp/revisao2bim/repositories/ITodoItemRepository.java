package fipp.revisao2bim.repositories;

import fipp.revisao2bim.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoItemRepository extends JpaRepository<TodoItem,Long> {
}
