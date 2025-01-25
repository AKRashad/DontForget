package com.example.DoNotForget.UserItems;

import com.example.DoNotForget.ExceptionHandle.UserNameDuplicateException;
import com.example.DoNotForget.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public String addNewUser(AppUser appUser)
    {

        if( appUserRepo.findByUserName(appUser.getUserName()) !=null ) {
            throw new UserNameDuplicateException("USer Name Alerady Taken");
        }
        appUser.setPassWord(encoder.encode(appUser.getPassWord()));
        appUserRepo.save(appUser);
        return  "User Add successfully";
    }

    public AppUser verfiy(AppUser user) throws Exception {
        Authentication authentication =  authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassWord()));
        if(authentication.isAuthenticated())
        {
            AppUser storedUser = appUserRepo.findByUserName(user.getUserName());

            if (storedUser == null) {
                throw new Exception("User not found");
            }

            return storedUser;
        }
        return  null;
    }
}
