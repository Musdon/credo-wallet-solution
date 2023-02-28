package com.musdon.simplewalletsystem.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class WalletRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
}
