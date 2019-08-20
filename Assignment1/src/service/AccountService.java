package service;

import model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account save(Account account);

    void delete(Long id);

}
