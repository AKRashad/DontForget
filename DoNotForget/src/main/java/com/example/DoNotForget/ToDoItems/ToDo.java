package com.example.DoNotForget.ToDoItems;

import com.example.DoNotForget.UserItems.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String todoSubject;
    private LocalDate todoDate;
    private boolean todoIsDone;
    @ManyToOne
    private AppUser appUser;

public boolean gettodoIsDone()
{
    return todoIsDone;
}



}
