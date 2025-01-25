package com.example.DoNotForget.UserItems;

import com.example.DoNotForget.Security.JwtService;
import com.example.DoNotForget.Security.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private JwtService jwtService;

    @PostMapping(path = "register")
    public String addNewUser(@RequestBody AppUser appUser) {
        return appUserService.addNewUser(appUser);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AppUser appUser) {
        try {
            AppUser authenticatedUser = appUserService.verfiy(appUser);
            String token = jwtService.generateToken(authenticatedUser.getUserName());
            return ResponseEntity.ok(new LoginResponse(token)); // Return the token wrapped in a JSON response
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials"));
        }


    }
}