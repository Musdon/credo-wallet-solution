package com.musdon.simplewalletsystem.controller;

import com.musdon.simplewalletsystem.model.Wallet;
import com.musdon.simplewalletsystem.payload.CreditAccountRequest;
import com.musdon.simplewalletsystem.payload.DebitAccountRequest;
import com.musdon.simplewalletsystem.payload.WalletRequest;
import com.musdon.simplewalletsystem.service.WalletService;
import com.musdon.simplewalletsystem.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    WalletService service;

    @PostMapping("/createAccount")
    public Response createAccount(@Valid @RequestBody WalletRequest walletRequest){
        return service.createWallet(walletRequest);
    }

    @PutMapping("/debit/{accountNumber}")
    public Response debitAccount(@Valid @RequestBody DebitAccountRequest request,
                                 @PathVariable String accountNumber){
        return service.debitAccount(request, accountNumber);
    }

    @PutMapping("/credit/{accountNumber}")
    public Response creditAccount(@Valid @RequestBody CreditAccountRequest request,
                                  @PathVariable String accountNumber){
        return service.creditAccount(request, accountNumber);
    }

    @GetMapping("/accounts")
    public List<Wallet> fetchAccounts(){
        return service.viewAllAccounts();
    }
}
