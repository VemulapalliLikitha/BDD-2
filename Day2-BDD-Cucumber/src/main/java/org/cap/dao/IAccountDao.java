package org.cap.dao;

import org.cap.model.Account;

public interface IAccountDao {
 public boolean addAccount(Account account);

public Account findAccountById(int accountNo);
public Account updateBalance(int accountNo,double amount);
}
