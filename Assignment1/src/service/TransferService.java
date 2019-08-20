package service;

import model.Transfer;
import model.User;

import java.sql.Date;
import java.util.List;

public interface TransferService {
    Transfer save(Transfer transfer);

    List<Transfer> findAll();

    List<Transfer> findByDateAndUser(Date start,Date end, User user);
}
