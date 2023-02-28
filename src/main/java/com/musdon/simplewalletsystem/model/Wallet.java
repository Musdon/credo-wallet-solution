package com.musdon.simplewalletsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String firstName;
    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String lastName;
    @Column(nullable = true)
    @Size(min = 3, max = 50)
    private String middleName;
    @Column(nullable = false, unique = true)
    @Size(max = 50)
    @Email
    private String email;
    @Column(nullable = false)
    @Size(min = 10, max = 10)
    private String accountNumber;
    private Double balance;
    private String status;

}
