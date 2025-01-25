package com.example.DoNotForget.Security;

import com.example.DoNotForget.UserItems.AppUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUsesrDetailsServices implements UserDetailsService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (appUserRepo.findByUserName(username) != null) {
            MyUserDetails myUserDetails = new MyUserDetails(appUserRepo.findByUserName(username));

            return myUserDetails;

        }
        System.out.println("Not Found");
       return  null;
    }
}
