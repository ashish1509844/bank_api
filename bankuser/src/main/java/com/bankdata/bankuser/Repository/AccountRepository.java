package com.bankdata.bankuser.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankdata.bankuser.Model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
