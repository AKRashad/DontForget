package com.example.DoNotForget.ToDoItems;

import com.example.DoNotForget.ExceptionHandle.ToDoRecordNotFoundException;
import com.example.DoNotForget.ExceptionHandle.UserNotFoundException;
import com.example.DoNotForget.Security.JwtService;
import com.example.DoNotForget.UserItems.AppUser;
import com.example.DoNotForget.UserItems.AppUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServices {
    @Autowired
    private ToDoRepo toDoRepo;

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private JwtService jwtService;


    public String addNewTodo(ToDo toDo) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String userName = jwtService.extractUserName(token);
        AppUser appUser = appUserRepo.findByUserName(userName);
        toDo.setAppUser(appUser);
        toDoRepo.save(toDo);
        return "ToDo Subject Added Successfully";
    }

    public List<ToDo> getAllToDoForUser() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String userName = jwtService.extractUserName(token);
        return toDoRepo.getAllToDoForUser(userName);
    }

    public List<ToDo> findToDoBySubjectLike(String subSubject) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String userName = jwtService.extractUserName(token);
        return toDoRepo.findToDoBySubjectLike(subSubject, userName);
    }

    public String deleteToDo(long id) throws UserNotFoundException {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String userName = jwtService.extractUserName(token);
        if (appUserRepo.findByUserName(userName) == null) {
            throw new UserNotFoundException("Impossible To Happen");
        }
        if (toDoRepo.findById(id) == null) {
            throw new ToDoRecordNotFoundException("No ToDo With This id");
        }
        int status = toDoRepo.deleteToDoByIdAndUserName(id, userName);
        return status == 0 ? "No ToDo Deleted" : "ToDo Deleted successfully ";
    }

    public String updateToDo(ToDo toDo) {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization").substring(7);
        String userName = jwtService.extractUserName(token);
        AppUser appUser = appUserRepo.findByUserName(userName);
        toDo.setAppUser(appUser);
        ToDo toDo1 = toDoRepo.findById(toDo.getId()).get();

        toDo1.setTodoSubject(toDo.getTodoSubject());
        toDo1.setTodoDate(toDo.getTodoDate());
        toDo1.setTodoIsDone(toDo.gettodoIsDone());

        toDoRepo.save(toDo1);
        return "ToDo Updated Successfully";


    }
}