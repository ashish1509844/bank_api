package com.bankdata.bankuser.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankdata.bankuser.Model.Account;
import com.bankdata.bankuser.Services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account create(@Valid @RequestBody Account acc) {
        return accountService.createAc(acc);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@Valid @PathVariable Long id) {
        return accountService.getById(id);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @Valid @RequestBody Account acc) {
        return accountService.update(id, acc);
    }

    @DeleteMapping("/{id}")
    public String delete(@Valid @PathVariable Long id) {
        return accountService.delete(id);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @Valid @RequestBody Map<String, Double> body) {
        Double amount = body.get("amount");
        return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @Valid @RequestBody Map<String, Double> body) {
        Double amount = body.get("amount");
        return ResponseEntity.ok(accountService.withdraw(id, amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody Map<String, Object> body) {
        Long fromId = Long.valueOf(body.get("fromAccountId").toString());
        Long toId = Long.valueOf(body.get("toAccountId").toString());
        Double amount = Double.valueOf(body.get("amount").toString());
        return ResponseEntity.ok(accountService.transfer(fromId, toId, amount));
    }
}
