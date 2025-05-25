package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.data.entity.User;
import com.lending.lendingbackend.data.repository.ClientRepository;
import com.lending.lendingbackend.dto.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client student) {
        return clientRepository.save(student);
    }

    public Client findClientByUserId(Long id) {
        return clientRepository.findClientByUser_Id(id);
    }
    public Client createClientByRegistrationDTO(User user, RegistrationDTO registrationDTO){
        Client client = new Client();
        client.setFirstName(registrationDTO.getFirstName());
        client.setLastName(registrationDTO.getLastName());
        client.setMiddleName(registrationDTO.getMiddleName());

        client.setPassportNumber(registrationDTO.getPassportNumber());
        client.setPassportSeries(registrationDTO.getPassportSeries());

        client.setBirthDate(registrationDTO.getBirthDate());
        client.setAddress(registrationDTO.getAddress());
        client.setEmail(registrationDTO.getEmail());
        client.setPhone(registrationDTO.getPhone());
        client.setUser(user);

        return save(client);
    }


}
