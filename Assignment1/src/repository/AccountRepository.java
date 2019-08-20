package repository;

import model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id);

    Account create(Account account);

    Account update(Account account);

    boolean deleteById(Long id);
}
