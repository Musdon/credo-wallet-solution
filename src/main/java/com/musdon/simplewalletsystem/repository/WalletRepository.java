package com.musdon.simplewalletsystem.repository;

import com.musdon.simplewalletsystem.model.Wallet;
import com.musdon.simplewalletsystem.utils.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByEmail(String email);
    Wallet findByAccountNumber(String accountNumber);


}
