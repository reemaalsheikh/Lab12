package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUsers(){
        return  authRepository.findAll();
    }

    public void register(User user){
    //user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);

        authRepository.save(user);
    }

    public void update(User user, Integer user_id){
        User user1 = authRepository.findUserById(user_id);

        if(user1 == null){
            throw new ApiException("User id not found");
        }
        user1.setUsername(user.getUsername());
        user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        authRepository.save(user1);
    }

    public void delete (Integer user_id) {
        User user1 = authRepository.findUserById(user_id);

        if(user1 == null) {
            throw new ApiException("User id not found");
        }

        authRepository.deleteById(user_id);
    }


}
