package com.lending.lendingbackend.service;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.data.repository.ClientRepository;
import com.lending.lendingbackend.dto.ClientDTO;
import com.lending.lendingbackend.dto.CreditApplicationDTO;
import com.lending.lendingbackend.dto.CreditDTO;
import com.lending.lendingbackend.dto.RegistrationDTO;
import com.lending.lendingbackend.service.convertor.ClientToClientDTOConverter;
import com.lending.lendingbackend.service.convertor.CreditApplicationToCreditApplicationDTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

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

    public List<ClientDTO> getAllClientDTOs(){
        return clientRepository.findAll().stream()
                .map(ClientToClientDTOConverter::convertClientToClientDTO)
                .collect(Collectors.toList());
    }


    public ClientDTO getClientDTOByUserId(Long userId){
        return ClientToClientDTOConverter.convertClientToClientDTO(findClientByUserId(userId));
    }
    public List<CreditApplicationDTO> getClientsApplicationListDTOByUserId(Long userId){
        return findClientByUserId(userId).getCreditApplications().stream()
                .map(CreditApplicationToCreditApplicationDTOConverter::convertCreditApplicationToCreditApplicationDTO)
                .collect(Collectors.toList());
    }


}
