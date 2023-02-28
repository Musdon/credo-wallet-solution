package com.musdon.simplewalletsystem.service;

import com.musdon.simplewalletsystem.model.Wallet;
import com.musdon.simplewalletsystem.utils.Response;
import com.musdon.simplewalletsystem.payload.CreditAccountRequest;
import com.musdon.simplewalletsystem.payload.DebitAccountRequest;
import com.musdon.simplewalletsystem.payload.WalletRequest;

import java.util.List;

public interface WalletService {

    Response createWallet(WalletRequest request);
    Response debitAccount(DebitAccountRequest request, String accountNumber);
    Response creditAccount(CreditAccountRequest request, String accountNumber);

    List<Wallet> viewAllAccounts();
}
