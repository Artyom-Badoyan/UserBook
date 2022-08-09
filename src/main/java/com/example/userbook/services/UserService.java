package com.example.userbook.services;

import com.example.userbook.exceptions.UserAlreadyExistException;
import com.example.userbook.exceptions.UserNotFoundException;
import com.example.userbook.models.User;
import com.example.userbook.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository = new UserRepository();
    private final static String USER_NOT_FOUND_ERROR_MSG = "Wrong email or password!";


    public User getByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User byEmail = userRepository.getByEmail(email);
        if(byEmail !=null && byEmail.getPassword().equals(password)){
            return byEmail;
        }else {
            throw new UserNotFoundException(USER_NOT_FOUND_ERROR_MSG);
        }
    }


    public User save(User user) throws UserAlreadyExistException {

        boolean exist = userRepository.existByEmail(user.getEmail());
        if(exist){
            throw  new UserAlreadyExistException("Email already used");
        }else{
          return userRepository.save(user);
        }
    }
}
