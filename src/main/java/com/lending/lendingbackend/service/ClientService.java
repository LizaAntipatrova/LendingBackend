package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.Client;
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
    public String  createClientByRegistrationDTO(RegistrationDTO registrationDTO){
        return clientRepository.addClient(
                registrationDTO.getLastName(),
                registrationDTO.getFirstName(),
                registrationDTO.getMiddleName(),
                registrationDTO.getPassportSeries(),
                registrationDTO.getPassportNumber(),
                registrationDTO.getBirthDate(),
                registrationDTO.getAddress(),
                registrationDTO.getPhone(),
                registrationDTO.getEmail(),
                registrationDTO.getLogin(),
                registrationDTO.getPassword()
                );
    }


}
