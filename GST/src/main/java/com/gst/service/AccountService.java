package com.gst.service;

import java.util.List;

import com.gst.domain.Account;
import com.gst.domain.Accrelate;


public interface AccountService {
    Account checkLogin(String username, String password);
    Account register(Account acc);
    Account checkAccountExist(String username);
    Account update(Account acc);
    void delete(Account acc);
    List<Account> getAllAccount();
    List<Accrelate>findListAccountByWord (String email,String username);
    Account checkExistAccountById(int userid);
}
