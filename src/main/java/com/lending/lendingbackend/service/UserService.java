package com.lending.lendingbackend.service;

import com.lending.lendingbackend.auth.exceptions.data.ExistingUserWithThatUsernameException;
import com.lending.lendingbackend.auth.exceptions.data.UserNotFoundException;
import com.lending.lendingbackend.data.entity.User;
import com.lending.lendingbackend.data.repository.UserRepository;
import com.lending.lendingbackend.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ClientService clientService;

    private User save(User user) {
        return userRepository.save(user);
    }

    public User createClientUserByRegistrationDTO(RegistrationDTO registrationDTO) {

        if (userRepository.findUserByLogin(registrationDTO.getLogin()) != null) {
            throw new ExistingUserWithThatUsernameException();
        }
        User user = new User();
        user.setLogin(registrationDTO.getLogin());
        user.setPassword(registrationDTO.getPassword());
        clientService.createClientByRegistrationDTO(user, registrationDTO);
        return save(user);
    }


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
