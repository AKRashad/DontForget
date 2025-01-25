package com.example.DoNotForget.ToDoItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todo")
public class ToDoController {

    @Autowired
    private ToDoServices toDoServices;

    @PostMapping()
    public String addNewToDo(@RequestBody ToDo toDo) {
        return toDoServices.addNewTodo(toDo);
    }

    @GetMapping( )
    public List<ToDo> getAllToDoForUser() {
        return toDoServices.getAllToDoForUser();
    }

    @GetMapping(path = "bysubject")
    public List<ToDo> getToDoBySubject(@RequestParam String subSubject) {
        return toDoServices.findToDoBySubjectLike(subSubject);
    }

    @DeleteMapping("/{todoid}")
    public String deleteToDo(@PathVariable("todoid")  long id ) {
      return toDoServices.deleteToDo(id);
    }
    @PutMapping()
    public String updateToDo(@RequestBody ToDo toDo) {
        return toDoServices.updateToDo(toDo);
    }


}
