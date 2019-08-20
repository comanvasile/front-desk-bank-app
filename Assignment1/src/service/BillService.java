package service;

import model.Bill;
import model.User;

import java.sql.Date;
import java.util.List;

public interface BillService {
    Bill save(Bill bill);

    List<Bill> findAll();

    List<Bill> findByDateAndUser(Date start, Date end, User u);
}
