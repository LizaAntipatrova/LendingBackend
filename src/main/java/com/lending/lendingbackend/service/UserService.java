package com.lending.lendingbackend.service;

import com.lending.lendingbackend.exceptions.data.UserNotFoundException;
import com.lending.lendingbackend.data.entity.User;
import com.lending.lendingbackend.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClientService clientService;




    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    public User findUserByLogin(String login) {
        User findUser = userRepository.findUserByLogin(login);
        if (findUser == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findUserByLogin(login);
    }

}
