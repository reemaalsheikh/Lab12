package com.example.lab12.Controller;

import com.example.lab12.Model.User;
import com.example.lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) {
        authService.register(user);
        return ResponseEntity.status(200).body("User registered successfully!");
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity update(@Valid @RequestBody User user, @PathVariable Integer user_id) {
        authService.update(user,user_id);
        return ResponseEntity.status(200).body("User updated successfully!");
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity delete(@PathVariable Integer user_id) {
        authService.delete(user_id);
        return ResponseEntity.status(200).body("User deleted successfully!");
    }

}
