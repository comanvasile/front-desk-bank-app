package service.impl;

import model.Account;
import repository.AccountRepository;
import service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        if (account.getId() != null) {
            return accountRepository.update(account);
        } else {
            return accountRepository.create(account);
        }    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
