package com.lending.lendingbackend.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private List<CreditApplication> creditApplications;
    
    @ManyToMany
    @JoinTable(name = "manager_specializations",
               joinColumns = @JoinColumn(name = "manager_id"),
               inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private Set<CreditCategory> specializations;
}