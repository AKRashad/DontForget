package com.example.DoNotForget.ToDoItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {
    @Modifying
    @Query(value = "select todo_date,todo_is_done,todo_subject,donotforget.todo.id,donotforget.todo.app_user_id from donotforget.todo inner join donotforget.users on donotforget.todo.app_user_id = donotforget.users.id where user_name = :userName  ", nativeQuery = true)
    public List<ToDo> getAllToDoForUser(@Param("userName") String userName);

    @Query(value = "select todo_date,todo_is_done,todo_subject,donotforget.todo.id,donotforget.todo.app_user_id from donotforget.todo inner join donotforget.users on donotforget.todo.app_user_id = donotforget.users.id where user_name = :userName and todo_subject like  CONCAT ('%',:subSubject,'%' )  ", nativeQuery = true)
    public List<ToDo> findToDoBySubjectLike(@Param("subSubject") String subSubject, @Param("userName") String userName);
    @Modifying
    @Transactional
    @Query(value = "delete donotforget.todo from donotforget.todo inner join donotforget.users on donotforget.todo.app_user_id = donotforget.users.id where user_name = :userName and donotforget.todo.id =  :todoid", nativeQuery = true)
    public int deleteToDoByIdAndUserName(@Param("todoid") long id, @Param("userName") String userName);
}
