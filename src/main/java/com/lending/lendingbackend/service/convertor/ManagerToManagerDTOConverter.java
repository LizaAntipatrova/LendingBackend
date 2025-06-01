package com.lending.lendingbackend.service.convertor;

import com.lending.lendingbackend.data.entity.Manager;
import com.lending.lendingbackend.dto.ManagerDTO;

public class ManagerToManagerDTOConverter {
    public static ManagerDTO convertManagerToManagerDTO(Manager manager) {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setEmployeeId(manager.getEmployeeId());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setMiddleName(manager.getMiddleName());
        managerDTO.setPhone(manager.getPhone());
        managerDTO.setEmail(manager.getEmail());
        return managerDTO;
    }
}
