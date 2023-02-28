package com.musdon.simplewalletsystem.service;

import com.musdon.simplewalletsystem.utils.AccountUtil;
import com.musdon.simplewalletsystem.utils.Response;
import com.musdon.simplewalletsystem.model.Wallet;
import com.musdon.simplewalletsystem.payload.CreditAccountRequest;
import com.musdon.simplewalletsystem.payload.DebitAccountRequest;
import com.musdon.simplewalletsystem.payload.WalletRequest;
import com.musdon.simplewalletsystem.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    WalletRepository walletRepository;


    @Override
    public Response createWallet(WalletRequest request) {

        Response response = new Response();
        Wallet wallet = walletRepository.findByEmail(request.getEmail());
        final String accountNumber = AccountUtil.generateAccountNumber();
        if (wallet!=null){
            response.setResponseCode(AccountUtil.DUPLICATE_ACCOUNT);
            response.setResponseMessage(AccountUtil.DUPLICATE_ACCOUNT_MESSAGE);
        } else {

            wallet = Wallet.builder().firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .middleName(request.getMiddleName())
                    .email(request.getEmail())
                    .accountNumber(accountNumber)
                    .balance(0.0)
                    .status("Active")
                    .build();
            walletRepository.save(wallet);

            response.setAccountName(request.getFirstName() + " " + request.getMiddleName() + " " + request.getLastName());
            response.setBalance(0.0);
            response.setResponseCode(AccountUtil.ACCOUNT_CREATED);
            response.setResponseCode(AccountUtil.ACCOUNT_CREATED_MESSAGE);
            response.setAccountNumber(accountNumber);
        }


        return response;


    }

    @Override
    public Response debitAccount(DebitAccountRequest request, String accountNumber) {
        Response response = new Response();
        Wallet wallet = walletRepository.findByAccountNumber(accountNumber);
        String accountName = wallet.getFirstName() + " " + wallet.getLastName() + " " + wallet.getMiddleName();

        if (wallet != null){
            Double initialBalance = wallet.getBalance();

            if (initialBalance >= request.getAmount()){
                Double newBalance = initialBalance - request.getAmount();
                wallet.setBalance(newBalance);

                response.setResponseCode(AccountUtil.DEBIT_SUCCEEDED);
                response.setResponseMessage(AccountUtil.DEBIT_SUCCEEDED_MESSAGE);
                response.setAccountNumber(accountNumber);
                response.setAccountName(accountName);
                response.setBalance(newBalance);
            } else {
                wallet.setBalance(initialBalance);
                walletRepository.save(wallet);
                response.setResponseCode(AccountUtil.INSUFFICIENT_BALANCE);
                response.setResponseMessage(AccountUtil.INSUFFICIENT_BALANCE_MESSAGE);
                response.setBalance(initialBalance);
                response.setAccountNumber(accountNumber);
                response.setAccountName(accountName);

            }

        }
        return response;
    }

    @Override
    public Response creditAccount(CreditAccountRequest request, String accountNumber) {
        Response response = new Response();
        Wallet wallet = walletRepository.findByAccountNumber(accountNumber);


        if (wallet != null){
            Double initialBalance = wallet.getBalance();
            String accountName = wallet.getFirstName() + " " + wallet.getLastName() + " " + wallet.getMiddleName();
            Double newBalance = initialBalance + request.getAmount();
            wallet.setBalance(newBalance);
            walletRepository.save(wallet);
            response.setResponseCode(AccountUtil.CREDIT_SUCCEEDED);
            response.setResponseMessage(AccountUtil.CREDIT_SUCCEEDED_MESSAGE);
            response.setAccountNumber(accountNumber);
            response.setAccountName(accountName);
            response.setBalance(newBalance);
        } else {
            response.setResponseCode(AccountUtil.ACCOUNT_NOT_FOUND);
            response.setResponseMessage(AccountUtil.ACCOUNT_NOT_FOUND_MESSAGE);

        }
        return response;
    }

    @Override
    public List<Wallet> viewAllAccounts() {
        return walletRepository.findAll();
    }
}
