package com.example.DoNotForget.UserItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
     AppUser findByUserName(String userName);
}
