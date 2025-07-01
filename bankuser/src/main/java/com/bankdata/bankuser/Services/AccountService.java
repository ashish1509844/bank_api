package com.bankdata.bankuser.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankdata.bankuser.Model.Account;
import com.bankdata.bankuser.Repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAc(Account acc) {
        acc.setAcNo(null);
        if (acc.getName() != null && acc.getAcType() != null && acc.gettAmt() != 0.0) {
            return accountRepository.save(acc);
        } else {
            System.out.println("Invalid input");
            return null;
        }
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getById(Long id) {
        Optional<Account> acc = accountRepository.findById(id);
        if (acc.isPresent()) {
            return acc.get();
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public Account update(Long id, Account nAccount) {
        Optional<Account> existing = accountRepository.findById(id);

        if (existing.isPresent()) {
            Account acc = existing.get();

            acc.setName(nAccount.getName());
            acc.setAcType(nAccount.getAcType());
            acc.settAmt(nAccount.gettAmt());
            return accountRepository.save(acc);

        } else {
            throw new RuntimeException("Can nit update bank user,please try again!!!");
        }
    }

    public String delete(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return "delete bank account user";
        } else {
            return "account user not found";
        }
    }

    public Account deposit(Long id, double amount) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        acc.settAmt(acc.gettAmt() + amount);
        return accountRepository.save(acc);
    }

    public Account withdraw(Long id, Double amount) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (acc.gettAmt() >= amount) {
            acc.settAmt(acc.gettAmt() - amount);
            return accountRepository.save(acc);
        } else {
            throw new RuntimeException("Insufficient balance");
        }
    }

    public String transfer(Long fromId, Long toId, Double amount) {
        Account fromAcc = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        Account toAcc = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (fromAcc.gettAmt() >= amount) {
            fromAcc.settAmt(fromAcc.gettAmt() - amount);
            toAcc.settAmt(toAcc.gettAmt() + amount);
            accountRepository.save(fromAcc);
            accountRepository.save(toAcc);
            return "Transfer successful";
        } else {
            throw new RuntimeException("Insufficient funds");
        }
    }
}
