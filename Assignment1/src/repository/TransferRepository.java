package repository;

import model.Transfer;
import model.User;

import java.sql.Date;
import java.util.List;

public interface TransferRepository {
    List<Transfer> findAll();

    List<Transfer> findByDateAndUser(Date start, Date end, User user);

    Transfer create(Transfer transfer);

    Transfer update(Transfer transfer);
}
