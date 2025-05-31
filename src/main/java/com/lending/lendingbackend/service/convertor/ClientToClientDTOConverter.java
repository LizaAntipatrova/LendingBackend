package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.Client;
import com.lending.lendingbackend.dto.ClientDTO;

public class ClientToClientDTOConverter {
    public static ClientDTO convertClientToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setAccountNumber(client.getAccountNumber());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setMiddleName(client.getMiddleName());
        clientDTO.setPassportSeries(client.getPassportSeries());
        clientDTO.setPassportNumber(client.getPassportNumber());
        clientDTO.setBirthDate(client.getBirthDate());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }
}
